package group3191.utils;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Reads environment variables from .env file
 */
public class Settings 
{
    private static Dotenv dotenv;
    public static final String OPENWEATHER_URI= "https://api.openweathermap.org/data/2.5/";
    public static final String URI_OW_LOCATION = "http://api.openweathermap.org/geo/1.0";
    public static final String PRO_OPENWEATHER_URI = "https://pro.openweathermap.org/data/2.5/";

    /**
     * Only initialized once at the start of the application
     */
    public static void Init() {
        dotenv = Dotenv.configure().load();
    }

    /**
     * Gets the value of the environment variable with the given key
     * @param key The key of the environment variable
     * @return The value of the environment variable
     */
    public static String GetValue(String key) {
        return dotenv.get(key);
    }

    /**
     * Gets the value of the environment variable with the given key
     * @param key The key of the environment variable
     * @param defaultValue The default value to return if the environment variable is not found
     * @return The value of the environment variable
     */
    public static String GetValue(String key, String defaultValue) {
        return dotenv.get(key, defaultValue);
    }
}
