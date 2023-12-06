/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

import group3191.models.weather.Clouds;
import group3191.models.weather.Weather;
import group3191.models.weather.WeatherDTO;
import group3191.models.weather.DailyWeatherDTO;

/**
 *
 * @author EeroP
 */
public class WeatherIconMapping {

    public static String getIconCodeByWeather(WeatherDTO weatherDTO) {
        String toReturn = "";

        Weather weather = weatherDTO.getWeather().get(0);
        if (weather != null) {
            Clouds clouds = weatherDTO.getClouds();
            switch (weather.getMain().strip()) {
                case "Thunderstorm":
                    toReturn = "45";
                    break;
                case "Drizzle":
                    toReturn = "07";
                    break;
                case "Rain":
                    toReturn = handleRain(weather, clouds);
                    break;
                case "Snow":
                    toReturn = handleSnow(weather, clouds);
                    break;
                case "Atmosphere":
                    toReturn = "06";
                    break;
                case "Clear":
                    toReturn = "01";
                    toReturn = handleClearClouds(clouds);
                    break;
                case "Clouds":
                    toReturn = "04";
                    toReturn = handleClearClouds(clouds);
                    break;

            }

            if (weather.getIcon().contains("d")) {
                toReturn += "d";
            }
            if (weather.getIcon().contains("n")) {
                toReturn += "n";
            }
        }

        return toReturn;
    }
    
    public static String getIconCodeByWeather(DailyWeatherDTO dailyWeatherDTO) {
        String toReturn = "";

        Weather weather = dailyWeatherDTO.getWeather().get(0);
        if (weather != null) {
            int cloudAmmount = dailyWeatherDTO.getClouds();
            
            switch (weather.getMain().strip()) {
                case "Thunderstorm":
                    toReturn = "45";
                    break;
                case "Drizzle":
                    toReturn = "07";
                    break;
                case "Rain":
                    toReturn = handleRain(weather, cloudAmmount);
                    break;
                case "Snow":
                    toReturn = handleSnow(weather, cloudAmmount);
                    break;
                case "Atmosphere":
                    toReturn = "06";
                    break;
                case "Clear":
                    toReturn = "01";
                    toReturn = handleClearClouds(cloudAmmount);
                    break;
                case "Clouds":
                    toReturn = "04";
                    toReturn = handleClearClouds(cloudAmmount);
                    break;

            }

            if (weather.getIcon().contains("d")) {
                toReturn += "d";
            }
            if (weather.getIcon().contains("n")) {
                toReturn += "n";
            }
        }

        return toReturn;
    }
    

    public static String handleClearClouds(Clouds clouds) {
        String toReturn = "";

        int cloudAmmount = clouds.getAll();
        toReturn = handleClearClouds(cloudAmmount);

        return toReturn;
    }
    
    public static String handleClearClouds(int cloudAmmount) {
        String toReturn = "";

        if (0 <= cloudAmmount && cloudAmmount < 11) //low clouds
        {
            toReturn = "01";
        }
        if (11 <= cloudAmmount && cloudAmmount < 25) //mostly clear
        {
            toReturn = "02";
        }
        if (25 <= cloudAmmount && cloudAmmount < 50) //half cloudy
        {
            toReturn = "03";
        }
        if (50 <= cloudAmmount && cloudAmmount < 84) // mostly cloudy
        {
            toReturn = "04";
        }
        if (84 <= cloudAmmount) // mostly cloudy
        {
            toReturn = "05";
        }

        return toReturn;
    }

    public static String handleSnow(Weather weather, Clouds clouds) {
        String toReturn = "";
        String description = weather.getDescription();
        int cloudAmmount = clouds.getAll();
        toReturn = handleSnow(weather, cloudAmmount);

        return toReturn;
    }
    
    public static String handleSnow(Weather weather, int cloudAmmount) {
        String toReturn = "";
        String description = weather.getDescription();

        switch (description.strip()) {
            case "light snow":
                toReturn = "37";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "37";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "31";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "34";
                }
                break;
            case "snow":
                toReturn = "38";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "38";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "32";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "35";
                }
                break;
            case "heavy snow":
                toReturn = "39";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "39";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "33";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "36";
                }
                break;
            case "sleet":
                toReturn = "29";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "29";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "23";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "26";
                }
                break;
            case "light shower sleet":
                toReturn = "28";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "28";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "22";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "25";
                }
                break;
            case "shower sleet":
                toReturn = "28";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "28";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "22";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "25";
                }
                break;
            case "rain and snow":
                toReturn = "29";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "29";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "23";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "26";
                }
                break;
            case "light shower snow":
                toReturn = "37";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "37";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "31";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "34";
                }
                break;
            case "shower snow":
                toReturn = "37";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "37";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "31";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "34";
                }
                break;
            case "heavy shower snow":
                toReturn = "39";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "39";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "33";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "36";
                }
                break;

        }
        return toReturn;
    }
    
    public static String handleRain(Weather weather, int cloudAmmount) {
        String toReturn = "";
        String description = weather.getDescription();

        switch (description.strip()) {
            default:
                toReturn = "19";
                break;
            case "light rain":
                toReturn = "19";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "19";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "13";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "16";
                }
                break;
            case "moderate rain":
                toReturn = "20";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "20";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "14";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "17";
                }
                break;
            case "heavy intensity rain":
                toReturn = "21";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "21";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "15";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "18";
                }
                break;
            case "very heavy rain":
                toReturn = "21";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "21";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "15";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "18";
                }
                break;
            case "extreme rain":
                toReturn = "21";
                if (0 <= cloudAmmount && cloudAmmount < 25) //low clouds
                {
                    toReturn = "21";
                }
                if (25 <= cloudAmmount && cloudAmmount < 50) //moderate clouds
                {
                    toReturn = "15";
                }
                if (50 <= cloudAmmount) // mostly cloudy
                {
                    toReturn = "18";
                }
                break;
            case "freezing rain":
                toReturn = "09";
                break;
            case "light intensity shower rain":
                toReturn = "10";
                break;
            case "shower rain":
                toReturn = "12";
                break;
            case "heavy intensity shower rain":
                toReturn = "12";
                break;
            case "ragged shower rain":
                toReturn = "12";
                break;
        }
        return toReturn;
    }

    public static String handleRain(Weather weather, Clouds clouds) {
        String toReturn = "";
        String description = weather.getDescription();
        int cloudAmmount = clouds.getAll();
        toReturn = handleRain(weather, cloudAmmount);
        return toReturn;
    }

    public static String getLocalWeatherIconCode(String code, String main, String description, String icon) {
        String toReturn = "";

        switch (main.strip()) {
            case "Thunderstorm":
                toReturn = "45";
                break;
            case "Drizzle":
                toReturn = "07";
                break;
            case "Rain":
                toReturn = handleDescriptionRain(description);
                break;
            case "Snow":
                toReturn = handleDescriptionSnow(description);
                break;
            case "Atmosphere":
                toReturn = "06";
                break;
            case "Clear":
                toReturn = "01";
                break;
            case "Clouds":
                toReturn = "04";
                break;

        }

        if (icon.contains("d")) {
            toReturn += "d";
        }
        if (icon.contains("n")) {
            toReturn += "n";
        }

        return toReturn;
    }

    private static String handleDescriptionRain(String description) {
        String toReturn = "";
        switch (description.strip()) {
            default:
                toReturn = "19";
                break;
            case "light rain":
                toReturn = "19";
                break;
            case "moderate rain":
                toReturn = "20";
                break;
            case "heavy intensity rain":
                toReturn = "21";
                break;
            case "very heavy rain":
                toReturn = "21";
                break;
            case "extreme rain":
                toReturn = "21";
                break;
            case "freezing rain":
                toReturn = "09";
                break;
            case "light intensity shower rain":
                toReturn = "10";
                break;
            case "shower rain":
                toReturn = "12";
                break;
            case "heavy intensity shower rain":
                toReturn = "12";
                break;
            case "ragged shower rain":
                toReturn = "12";
                break;

        }
        return toReturn;
    }

    private static String handleDescriptionSnow(String description) {
        String toReturn = "";
        switch (description.strip()) {
            case "light snow":
                toReturn = "37";
                break;
            case "snow":
                toReturn = "38";
                break;
            case "heavy snow":
                toReturn = "39";
                break;
            case "sleet":
                toReturn = "29";
                break;
            case "light shower sleet":
                toReturn = "28";
                break;
            case "shower sleet":
                toReturn = "28";
                break;
            case "rain and snow":
                toReturn = "29";
                break;
            case "light shower snow":
                toReturn = "37";
                break;
            case "shower snow":
                toReturn = "37";
                break;
            case "heavy shower snow":
                toReturn = "38";
                break;

        }
        return toReturn;
    }

}
