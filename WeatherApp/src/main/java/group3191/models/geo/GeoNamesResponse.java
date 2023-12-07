/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.models.geo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author EeroP
 */
public class GeoNamesResponse {
    @SerializedName("geonames")
    private List<GeoName> geoNames;

    public List<GeoName> getGeoNames() {
        return geoNames;
    }
    public int getSize()
    {
        return this.geoNames.size();
    }
    public GeoName getIndex(int index)
    {
        return this.geoNames.get(index);
    }
    
    public List<GeoName> getList()
    {
        return this.geoNames;
    }
}
    

