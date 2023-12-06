package group3191.utils;

import java.net.http.HttpResponse;

import group3191.db.DatabaseHandler;
import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;

/**
 * This class is used to make requests to the OpenWeather API.
 */
public class Api implements iAPI {

    /**
     * Constructor for the Api class.
     */
    public Api() {}

    @Override
    public String lookUpLocation(String loc) {
        String endpoint = "find";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("q", loc);
        OpenWeatherRequest request = new OpenWeatherRequest(
                Settings.OPENWEATHER_URI, requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();
        return response.body();

    }

    @Override
    public String getCurrentWeather(double lat, double lon) {
        String endpoint = "weather";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", DatabaseHandler.getUnitValue());
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(Settings.OPENWEATHER_URI, requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

    @Override
    public String getForecast(double lat, double lon) {
        String endpoint = "forecast";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", DatabaseHandler.getUnitValue());
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        // Use pro endpoint for forecast to get 1 hour interval data
        String baseUrl = "true".equalsIgnoreCase(Settings.GetValue("true")) ? Settings.PRO_OPENWEATHER_URI : Settings.OPENWEATHER_URI;
        OpenWeatherRequest request = new OpenWeatherRequest(baseUrl, requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

    @Override
    public String getAirPollution(double lat, double lon) {
        String endpoint = "air_pollution";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", DatabaseHandler.getUnitValue());
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(Settings.OPENWEATHER_URI, requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

    @Override
    public String getDailyForecast(double lat, double lon) {
        String endpoint = "forecast/daily";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("units", DatabaseHandler.getUnitValue());
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(Settings.OPENWEATHER_URI, requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }
}
