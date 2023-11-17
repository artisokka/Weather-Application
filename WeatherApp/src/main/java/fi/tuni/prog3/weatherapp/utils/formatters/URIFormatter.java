package fi.tuni.prog3.weatherapp.utils.formatters;

/**
 * This interface is used to format a URI.
 */
public interface URIFormatter {
    public void AddParameter(String key, String value);
    //public void AddKey(String key);
    public String GetURI();
}
