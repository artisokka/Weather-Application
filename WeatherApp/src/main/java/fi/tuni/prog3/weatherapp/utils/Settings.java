package fi.tuni.prog3.weatherapp.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Settings 
{
    private static Dotenv dotenv;

    public Settings() {
        // reads environment variables from .env file
    }

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
