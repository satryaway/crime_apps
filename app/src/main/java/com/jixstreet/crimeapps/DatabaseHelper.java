package com.jixstreet.crimeapps;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jixstreet.crimeapps.models.Crime;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 9/3/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "crimeApp.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 2;
    private Context _context;

    private Dao<Crime, Integer> categoryDao;

    private RuntimeExceptionDao<Crime, Integer> crimeRuntimeExceptionDao = null;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
        crimeRuntimeExceptionDao = getRuntimeExceptionDao(Crime.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Crime.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    public Crime getCrimeById(int id) {
        return crimeRuntimeExceptionDao.queryForId(id);
    }

    public Dao<Crime, Integer> getCrimeDao() {
        if (categoryDao == null)
            try {
                categoryDao = DaoManager.createDao(getConnectionSource(), Crime.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return categoryDao;
    }

    public List<Crime> getAllCrimes() {
        QueryBuilder<Crime, Integer> queryBuilder = crimeRuntimeExceptionDao.queryBuilder().orderBy("id", true);
        PreparedQuery<Crime> preparedQuery = null;
        try {
            preparedQuery = queryBuilder.prepare();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crimeRuntimeExceptionDao.query(preparedQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Crime.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        crimeRuntimeExceptionDao = null;
    }
}
