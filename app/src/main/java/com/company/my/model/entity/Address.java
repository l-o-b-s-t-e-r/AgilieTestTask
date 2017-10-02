package com.company.my.model.entity;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

/**
 * Created by Lobster on 30.09.17.
 */

public class Address extends RealmObject {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private Location geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Location getGeo() {
        return geo;
    }

    public void setGeo(Location geo) {
        this.geo = geo;
    }

    public LatLng getLatLng() {
        return new LatLng(geo.getLat(), geo.getLng());
    }
}
