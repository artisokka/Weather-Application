package group3191.utils;

import java.net.http.HttpResponse;

import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;
import java.net.http.HttpResponse;

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
