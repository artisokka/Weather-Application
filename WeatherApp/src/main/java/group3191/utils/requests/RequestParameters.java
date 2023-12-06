package group3191.utils.requests;

import java.util.TreeMap;

/**
 * This class is used to store parameters for a request.
 */
public class RequestParameters {
    
    TreeMap<String, String> parameters;

    /**
     * Constructor for the RequestParameters class.
     */
    public RequestParameters() {
        parameters = new TreeMap<>();
    }

    /**
     * This method adds a parameter to the request.
     * @param key The key of the parameter.
     * @param value The value of the parameter.
     */
    public void Add(String key, String value) {
        parameters.put(key, value);
    }

    /**
     * This method returns the value of a parameter.
     * @param key The key of the parameter.
     * @return The value of the parameter.
     */
    public String Get(String key) {
        return parameters.get(key);
    }

    /**
     * This method returns the parameters of the request.
     * @return The parameters of the request.
     */
    public TreeMap<String, String> GetParameters() {
        return parameters;
    }

}
