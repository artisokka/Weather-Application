package fi.tuni.prog3.weatherapp.controllers;

import fi.tuni.prog3.weatherapp.models.WeatherDTO;
import fi.tuni.prog3.weatherapp.models.WeatherModel;

public class WeatherController {
    private WeatherModel weatherModel;
    public WeatherController() {
        weatherModel = new WeatherModel();
    }


    public WeatherDTO getCurrentWeather(double longitude, double latitude) {
        return weatherModel.getCurrentWeather(longitude, latitude);
    }

}
