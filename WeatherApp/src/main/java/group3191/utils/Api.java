package group3191.utils;

import com.google.gson.Gson;
import group3191.models.geo.GeoName;
import group3191.models.geo.GeoNamesResponse;
import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Api implements iAPI {

    public Api() {}

    @Override
    public String lookUpLocation(String loc) {
        try {
            GeoNamesResponse geoNamesResponse = ApiHandlerGeoNames.
                    getLocationSuggestions(loc);

            if (geoNamesResponse != null && geoNamesResponse.getGeoNames() !=
                    null &&
                    !geoNamesResponse.getGeoNames().isEmpty()) {
                GeoName firstSuggestion = geoNamesResponse.getGeoNames().get(0);
                System.out.println("First Geonames Suggestion: " +
                        firstSuggestion.toString());

                return "Latitude: " + firstSuggestion.getLat() + ", Longitude: " +
                        firstSuggestion.getLng();
            } else {
                System.out.println("No Geonames suggestions found.");
                throw new IOException("No suggestions found for location: " +
                        loc);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }


    @Override
    public String getCurrentWeather(double lat, double lon) {
        String endpoint = "weather";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", "metric");
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

    @Override
    public String getForecast(double lat, double lon) {
        String endpoint = "forecast";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", "metric");
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

}
