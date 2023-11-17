package fi.tuni.prog3.weatherapp.utils.requests;

import java.util.TreeMap;

public class RequestParameters {
    
    TreeMap<String, String> parameters;

    public RequestParameters() {
        parameters = new TreeMap<>();
    }

    public void Add(String key, String value) {
        parameters.put(key, value);
    }

    public String Get(String key) {
        return parameters.get(key);
    }

    public TreeMap<String, String> GetParameters() {
        return parameters;
    }

}
