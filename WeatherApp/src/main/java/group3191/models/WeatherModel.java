package group3191.models;

import com.google.gson.Gson;

import group3191.utils.Api;

public class WeatherModel {
    private Api api;

    public WeatherModel() {
        api = new Api();
    }

    public WeatherDTO getCurrentWeather(double longitude, double latitude) {
        Gson gson = new Gson();
        String responseBody = api.getCurrentWeather(longitude, latitude);
        System.out.println(responseBody);
        WeatherDTO weatherDTO = gson.fromJson(responseBody, WeatherDTO.class);
        return weatherDTO;
    }
}
