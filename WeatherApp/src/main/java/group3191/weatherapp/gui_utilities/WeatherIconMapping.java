/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

/**
 *
 * @author EeroP
 */
public class WeatherIconMapping {

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
