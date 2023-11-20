/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author EeroP
 */
public class GeoName {
    private String name;
    private String countryName;
    private double lat;
    private double lng;

    public String getCityName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return this.getCityName() + ", " + countryName + ", lat=" + lat + ", long=" + lng;
    }

   
}
