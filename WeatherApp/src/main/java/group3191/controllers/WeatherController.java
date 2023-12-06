package group3191.controllers;

import java.util.List;

import group3191.models.WeatherModel;
import group3191.models.weather.DailyWeatherDTO;
import group3191.models.weather.WeatherDTO;

/**
 * This class is used between frontend and backend.
 */
public class WeatherController {
    private WeatherModel weatherModel;

    /**
     * This constructor is used to create a new weather controller.
     */
    public WeatherController() {
        weatherModel = new WeatherModel();
    }

    /**
     * This method returns the current weather for a location.
     * @param location The location to get the weather for.
     * @return The current weather for the location.
     */
    public WeatherDTO lookUpLocation(String location) {
        return weatherModel.lookUpLocation(location);
    }

    /**
     * This method returns the current weather for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The current weather for the location.
     */
    public WeatherDTO getCurrentWeather(double latitude, double longitude) {
        return weatherModel.getCurrentWeather(latitude, longitude);
    }

    /**
     * This method returns the forecast for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The forecast for the location.
     */
    public List<WeatherDTO> getForecast(double latitude, double longitude) {
        return weatherModel.getForecast(latitude, longitude);
    }

    /**
     * This method returns the daily forecast for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The daily forecast for the location.
     */
    public List<DailyWeatherDTO> getDailyForecast(double latitude, double longitude) {
        return weatherModel.getDailyForecast(latitude, longitude);
    }

}
