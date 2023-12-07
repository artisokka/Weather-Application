/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.utils;

import com.google.gson.Gson;

import group3191.models.geo.GeoNamesResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author EeroP
 */
/**
 * This class handles API requests to GeoNames service for location suggestions.
 */
public class ApiHandlerGeoNames {
    private static String RESULTS_MAX_ROWS ="10"; // This could be moved to settings where the user selects how many results to display
    
    /**
     * Retrieves location suggestions based on the partial input.
     * 
     * @param partialInput The partial input for location search.
     * @return The GeoNamesResponse object containing the location suggestions.
     */
    public static GeoNamesResponse getLocationSuggestions(String partialInput) {
        String username = Settings.GetValue("GEONAMES_USERNAME");

        try {
            String urlString = "http://api.geonames.org/searchJSON?name_startsWith=" +
                    partialInput +
                    "&maxRows="+RESULTS_MAX_ROWS+"&username=" +
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
            GeoNamesResponse geoNamesResponse = gson.fromJson(response.toString(), GeoNamesResponse.class);
            connection.disconnect();
            return geoNamesResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // Add doc-strings for other functions here
    
}
