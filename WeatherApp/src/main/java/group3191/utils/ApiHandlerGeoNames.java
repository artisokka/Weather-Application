/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import group3191.models.GeoNamesResponse;
import group3191.models.GeoName;


/**
 *
 * @author EeroP
 */
public class ApiHandlerGeoNames {
    
    public static GeoNamesResponse getLocationSuggestions(String partialInput) {

    String username = Settings.GetValue("GEONAMES_USERNAME");

    try {
        String urlString = "http://api.geonames.org/searchJSON?name_startsWith=" +
        //String urlString = "http://api.geonames.org/searchJSON?q=" +
                partialInput +
                "&maxRows=10&username=" +
                username;

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        Gson gson = new Gson();
        //System.out.println("response string: " + response.toString());
        GeoNamesResponse geoNamesResponse = gson.fromJson(response.toString(), GeoNamesResponse.class);
        connection.disconnect();
        return geoNamesResponse;
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;
}
    

    
}
