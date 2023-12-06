package group3191.utils.formatters;

/**
 * This interface is used to format a URI.
 */
public interface URIFormatter {
    /**
     * This method adds a parameter to the URI.
     * @param key parameter key
     * @param value parameter value
     */
    public void AddParameter(String key, String value);

    /**
     * This method returns the URI.
     * @return The URI.
     */
    public String GetURI();

    /**
     * This method returns the base URI.
     * @return The base URI.
     */
    public String GetBaseURI();
}
