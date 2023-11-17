package group3191.utils;

import java.net.http.HttpResponse;


import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;

public class Api implements iAPI {

    public Api() {}
    
    @Override
    public String lookUpLocation(String loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookUpLocation'");
    }

    @Override
    public String getCurrentWeather(double lat, double lon) {
        String endpoint = "weather";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        return response.body();
    }

    @Override
    public String getForecast(double lat, double lon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getForecast'");
    }
    
}
