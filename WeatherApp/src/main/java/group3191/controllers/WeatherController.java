package group3191.controllers;

import java.util.List;

import group3191.models.WeatherModel;
import group3191.models.weather.WeatherDTO;

public class WeatherController {
    private WeatherModel weatherModel;
    public WeatherController() {
        weatherModel = new WeatherModel();
    }

    public WeatherDTO lookUpLocation(String loc) {
        return weatherModel.lookUpLocation(loc);
    }

    public WeatherDTO getCurrentWeather(double longitude, double latitude) {
        return weatherModel.getCurrentWeather(longitude, latitude);
    }

    public List<WeatherDTO> getForecast(double longitude, double latitude) {
        return weatherModel.getForecast(longitude, latitude);
    }

}
