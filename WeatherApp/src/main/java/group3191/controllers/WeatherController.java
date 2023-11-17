package group3191.controllers;

import group3191.models.WeatherDTO;
import group3191.models.WeatherModel;

public class WeatherController {
    private WeatherModel weatherModel;
    public WeatherController() {
        weatherModel = new WeatherModel();
    }


    public WeatherDTO getCurrentWeather(double longitude, double latitude) {
        return weatherModel.getCurrentWeather(longitude, latitude);
    }

}
