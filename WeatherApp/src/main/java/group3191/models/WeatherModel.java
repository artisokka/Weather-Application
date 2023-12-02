package group3191.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import group3191.models.weather.WeatherDTO;
import group3191.utils.Api;

public class WeatherModel {
    private Api api;

    public WeatherModel() {
        api = new Api();
    }

    public WeatherDTO getCurrentWeather(double latitude, double longitude) {
        Gson gson = new Gson();
        String responseBody = api.getCurrentWeather(latitude, longitude);
        System.out.println(responseBody);
        WeatherDTO weatherDTO = gson.fromJson(responseBody, WeatherDTO.class);
        return weatherDTO;
    }

    public List<WeatherDTO> getForecast(double latitude, double longitude) {
        String responseBody = api.getForecast(latitude, longitude);
        List<WeatherDTO> weatherDTOList = getWeatherList(responseBody);
        return weatherDTOList;
    }

    private List<WeatherDTO> getWeatherList(String responseBody) {
        List<WeatherDTO> weatherList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("list");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject weatherObject = jsonArray.get(i).getAsJsonObject();
            WeatherDTO weatherDTO = gson.fromJson(weatherObject, WeatherDTO.class);
            weatherList.add(weatherDTO);
        }

        return weatherList;
    }
}
