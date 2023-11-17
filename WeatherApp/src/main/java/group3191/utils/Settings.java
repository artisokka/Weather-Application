package group3191.utils;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Reads environment variables from .env file
 */
public class Settings 
{
    private static Dotenv dotenv;

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
