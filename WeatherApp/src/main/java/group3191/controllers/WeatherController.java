package group3191.controllers;

import java.util.List;

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

    public List<WeatherDTO> getForecast(double longitude, double latitude) {
        return weatherModel.getForecast(longitude, latitude);
    }

}
