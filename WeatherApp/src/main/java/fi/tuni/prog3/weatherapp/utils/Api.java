package fi.tuni.prog3.weatherapp.utils;

import java.net.http.HttpResponse;

import com.google.gson.Gson;

import fi.tuni.prog3.weatherapp.models.WeatherDTO;
import fi.tuni.prog3.weatherapp.utils.requests.OpenWeatherRequest;
import fi.tuni.prog3.weatherapp.utils.requests.RequestParameters;

public class Api implements iAPI {

    public Api() {}
    
    @Override
    public String lookUpLocation(String loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lookUpLocation'");
    }

    @Override
    public WeatherDTO getCurrentWeather(double lat, double lon) {
        String endpoint = "weather";
        RequestParameters requestParameters = new RequestParameters();
        requestParameters.Add("lat", String.valueOf(lat));
        requestParameters.Add("lon", String.valueOf(lon));
        OpenWeatherRequest request = new OpenWeatherRequest(requestParameters, endpoint);
        RestClient restClient = new RestClient(request);
        HttpResponse<String> response = restClient.getResponse();

        Gson gson = new Gson();
        WeatherDTO weatherDTO = gson.fromJson(response.body(), WeatherDTO.class);

        return weatherDTO;
    }

    @Override
    public String getForecast(double lat, double lon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getForecast'");
    }
    
}
