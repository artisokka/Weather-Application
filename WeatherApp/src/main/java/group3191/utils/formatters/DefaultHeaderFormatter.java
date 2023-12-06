package group3191.utils.formatters;

import java.util.TreeMap;

/**
 * This class is used to format headers for request
 */
public class DefaultHeaderFormatter implements HeaderFormatter {
    
    /**
     * Constructor for the DefaultHeaderFormatter class.
     */
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
