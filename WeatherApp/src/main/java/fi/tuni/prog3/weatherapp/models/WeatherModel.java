package fi.tuni.prog3.weatherapp.models;

import fi.tuni.prog3.weatherapp.utils.Api;

public class WeatherModel {
    private Api api;

    public WeatherModel() {
        api = new Api();
    }

    public WeatherDTO getCurrentWeather(double longitude, double latitude) {
        return api.getCurrentWeather(longitude, latitude);
    }
}
