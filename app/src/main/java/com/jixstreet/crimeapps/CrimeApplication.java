package com.jixstreet.crimeapps;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.sql.SQLException;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
public class CrimeApplication extends Application {
    private static CrimeApplication instance;
    private DatabaseHelper dbHelper;

    public synchronized static CrimeApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dbHelper = new DatabaseHelper(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public DatabaseHelper getDBHelperInstance() {
        return dbHelper;
    }

}
