/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.models.geo;

/**
 *
 * @author EeroP
 */
public class GeoName {
    private String name;
    private String countryName;
    private double lat;
    private double lng;

    public GeoName(String name, String countryName, double lat, double lng) {
        this.name = name;
        this.countryName = countryName;
        this.lat = lat;
        this.lng = lng;
    }

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

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
        {
            return false;
        }
        if(obj.getClass() != this.getClass())
        {
            return false;
        }
        
        final GeoName other = (GeoName)obj;
        if(other.getCityName() == null || other.getCountryName() == null)
        {
            return false;
        }
        if(!other.getCityName().equals(this.getCityName()) ||
                (!other.getCountryName().equals(this.getCountryName())) ||
                other.getLat() != this.getLat() || 
                        other.getLng() != this.getLng())
        {
            return false;
        }
        return true;
    }

   
}
