package group3191.utils.formatters;

import java.util.TreeMap;

/**
 * This class is used to format a URI for the OpenWeatherMap API.
 */
abstract class Formatter implements URIFormatter {
    protected String URI;
    protected String key;
    protected String endpoint;
    protected TreeMap<String, String> keyValues;

    /**
     * Constructor for the Formatter class.
     * @param URI The URI of the request.
     * @param endpoint The endpoint of the request.
     * @param key The API key of the request.
     */
    public Formatter(String URI, String endpoint, String key) {
        this.URI = URI;
        this.key = key;
        this.endpoint = endpoint;
        keyValues = new TreeMap<>();
    }

    @Override
    public abstract void AddParameter(String key, String value);

    @Override
    public abstract String GetURI();

    @Override
    public abstract String GetBaseURI();
}
