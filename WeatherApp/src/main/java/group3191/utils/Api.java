package group3191.utils;

import java.net.http.HttpResponse;


import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;

public class Api implements iAPI {

    public Api() {}

    @Override
    public String lookUpLocation(String loc) {
        String endpoint = "find";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("q", loc);
        requestParameters.Add("limit", "10");
        requestParameters.Add("appid", Settings.GetValue("API_KEY"));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters,
                endpoint);
        RestClient restClient = new RestClient(request);

        try {
            HttpResponse<String> response = restClient.getResponse();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during API request.";
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
