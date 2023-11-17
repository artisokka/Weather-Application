package fi.tuni.prog3.weatherapp.utils.formatters;

import java.util.TreeMap;

/**
 * This class is used to format headers for request
 */
public class DefaultHeaderFormatter implements HeaderFormatter {
    public DefaultHeaderFormatter() {
    
    }

    @Override
    public TreeMap<String, String> GetHeaders() {
        return new TreeMap<>();
    }

    @Override
    public void AddHeader(String key, String value) {
        return;
    }
}
