package group3191.utils.requests;

import java.util.TreeMap;

/**
 * This class is used to store parameters for a request.
 */
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
