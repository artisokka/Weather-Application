package group3191.utils;

import com.google.gson.Gson;
import group3191.models.geo.GeoName;
import group3191.models.geo.GeoNamesResponse;
import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Api implements iAPI {

    public Api() {
    }

    @Override
    public String lookUpLocation(String loc) {
        String endpoint = "find";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("q", loc);
        requestParameters.Add("limit", "10");
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters,
                endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();

    }

    @Override
    public String getCurrentWeather(double lat, double lon) {
        String endpoint = "weather";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", "metric");
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters,
                endpoint);
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
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters,
                endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

}
