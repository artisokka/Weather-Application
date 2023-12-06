package group3191.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import group3191.models.weather.AirQuality;
import group3191.models.weather.DailyWeatherDTO;
import group3191.models.weather.WeatherDTO;
import group3191.utils.Api;

/**
 * This class is used to get weather data from the API.
 */
public class WeatherModel {
    private Api api;

    /**
     * Constructor for the WeatherModel class.
     */
    public WeatherModel() {
        api = new Api();
    }

    /**
     * This method returns the current weather for a location.
     * @param loc The location to get the weather for.
     * @return The current weather for the location.
     */
    public WeatherDTO lookUpLocation(String loc) {
        List<WeatherDTO> weatherDTOList = new ArrayList<>();
        Gson gson = new Gson();
        String responseBody = api.lookUpLocation(loc);
        JsonObject jsonObject = JsonParser.parseString(responseBody).
                getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("list");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject weatherObject = jsonArray.get(i).getAsJsonObject();
            WeatherDTO weatherDTO = gson.fromJson(weatherObject,
                    WeatherDTO.class);
            weatherDTOList.add(weatherDTO);
        }

        return weatherDTOList.get(0);
    }

    /**
     * This method returns the current weather for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The current weather for the location.
     */
    public WeatherDTO getCurrentWeather(double latitude, double longitude) {
        Gson gson = new Gson();
        String responseBody = api.getCurrentWeather(latitude, longitude);
        JsonObject weatherObject = JsonParser.parseString(responseBody).getAsJsonObject();
        WeatherDTO weatherDTO = gson.fromJson(weatherObject, WeatherDTO.class);
        setAirPollution(weatherDTO);
        setPrecipitationAmount(weatherDTO, weatherObject, "snow");
        setPrecipitationAmount(weatherDTO, weatherObject, "rain");
        return weatherDTO;
    }

    /**
     * This method returns the forecast for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The forecast for the location.
     */
    public List<WeatherDTO> getForecast(double latitude, double longitude) {
        String responseBody = api.getForecast(latitude, longitude);
        List<WeatherDTO> weatherDTOList = getWeatherList(responseBody);
        return weatherDTOList;
    }

    /**
     * This method returns the daily forecast for a location.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The daily forecast for the location.
     */
    public List<DailyWeatherDTO> getDailyForecast(double latitude, double longitude) {
        String responseBody = api.getDailyForecast(latitude, longitude);
        List<DailyWeatherDTO> weatherDTOList = getDailyWeatherList(responseBody);
        return weatherDTOList;
    }

    /**
     * This method returns the proper weather DTOs from the API response.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The air pollution for the location.
     */
    private List<WeatherDTO> getWeatherList(String responseBody) {
        List<WeatherDTO> weatherList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("list");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject weatherObject = jsonArray.get(i).getAsJsonObject();
            WeatherDTO weatherDTO = gson.fromJson(weatherObject, WeatherDTO.class);
            setPrecipitationAmount(weatherDTO, weatherObject, "snow");
            setPrecipitationAmount(weatherDTO, weatherObject, "rain");
            weatherList.add(weatherDTO);
        }

        return weatherList;
    }

    /**
     * This method returns the proper daily weather DTOs from the API response.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     * @return The air pollution for the location.
     */
    private List<DailyWeatherDTO> getDailyWeatherList(String responseBody) {
        List<DailyWeatherDTO> weatherList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("list");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject weatherObject = jsonArray.get(i).getAsJsonObject();
            DailyWeatherDTO weatherDTO = gson.fromJson(weatherObject, DailyWeatherDTO.class);
            weatherList.add(weatherDTO);
        }

        return weatherList;
    }

    /**
     * This method sets the air pollution for a weather.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     */
    private void setAirPollution(WeatherDTO weatherDTO) {
        String pollutionResponse = api.getAirPollution(weatherDTO.getCoord().getLat(), weatherDTO.getCoord().getLon());
        JsonObject pollutionObject = JsonParser.parseString(pollutionResponse).getAsJsonObject();
        JsonArray pollutionList = pollutionObject.getAsJsonArray("list");
        JsonObject firstPollutionEntry = pollutionList.get(0).getAsJsonObject();
        JsonObject mainObject = firstPollutionEntry.getAsJsonObject("main");
        int aqiValue = mainObject.get("aqi").getAsInt();

        weatherDTO.setAirQuality(AirQuality.fromValue(aqiValue));
    }

    /**
     * This method sets the precipitation amount for a weather.
     * @param weatherDTO The weather DTO to set the precipitation amount for.
     * @param weatherObject The weather object to get the precipitation amount from.
     * @param precipitationType The type of precipitation to set the amount for. (snow or rain)
     */
    private void setPrecipitationAmount(WeatherDTO weatherDTO, JsonObject weatherObject, String precipitationType) {
        JsonObject precipitationObject = weatherObject.getAsJsonObject(precipitationType);
        if (precipitationObject != null) {
            JsonElement hourAmount = precipitationObject.get("1h");
            JsonElement threeHourAmount = precipitationObject.get("3h");

            double amount = (hourAmount != null) ? hourAmount.getAsDouble() : (threeHourAmount != null) ? threeHourAmount.getAsDouble() : 0.0;

            if ("snow".equals(precipitationType)) {
                weatherDTO.setSnowAmount(amount);
            } else if ("rain".equals(precipitationType)) {
                weatherDTO.setRainAmount(amount);
            }
        }
    }
}
