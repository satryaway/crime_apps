package com.jixstreet.crimeapps.models;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.jixstreet.crimeapps.CrimeApplication;

/**
 * Created by M Agung Satrio - agung.satrio@inmagine.com on 12/4/2015.
 */
@DatabaseTable(tableName = "crime")
public class Crime extends BaseDaoEnabled {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "address")
    private String address;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "type")
    private String type;
    @DatabaseField(columnName = "description")
    private String description;
    private LatLng latLng;
    @DatabaseField(columnName = "latitude")
    private double latitude;
    @DatabaseField(columnName = "longitude")
    private double longitude;
    @DatabaseField(columnName = "location")
    private String location;

    private Dao<Crime, Integer> objectDao = CrimeApplication.getInstance().getDBHelperInstance().getCrimeDao();

    public Crime() {
        setDao(objectDao);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
