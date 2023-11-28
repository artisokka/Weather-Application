package group3191.utils;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Reads environment variables from .env file
 */
public class Settings {

    private static Dotenv dotenv;
    public static final String OPENWEATHER_URI =
            "https://api.openweathermap.org/data/2.5/";
    public static final String URI_OW_LOCATION =
            "http://api.openweathermap.org/geo/1.0";
    public static final String GEONAMES_URI = "http://api.geonames.org/";

    public static void Init() {
        dotenv = Dotenv.configure().load();
    }

    public static String GetValue(String key) {
        return dotenv.get(key);
    }

    public static String GetValue(String key, String defaultValue) {
        return dotenv.get(key, defaultValue);
    }
}
