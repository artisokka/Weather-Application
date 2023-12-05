package group3191.controllers;

import java.util.List;

import group3191.models.WeatherModel;
import group3191.models.weather.WeatherDTO;

public class WeatherController {
    private WeatherModel weatherModel;
    public WeatherController() {
        weatherModel = new WeatherModel();
    }

    public WeatherDTO lookUpLocation(String location) {
        return weatherModel.lookUpLocation(location);
    }

    public WeatherDTO getCurrentWeather(double latitude, double longitude) {
        return weatherModel.getCurrentWeather(latitude, longitude);
    }

    public List<WeatherDTO> getForecast(double latitude, double longitude) {
        return weatherModel.getForecast(latitude, longitude);
    }

}
