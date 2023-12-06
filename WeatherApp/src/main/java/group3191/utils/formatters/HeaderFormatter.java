package group3191.utils.formatters;

import java.util.TreeMap;

/**
 * This interface is used to format headers.
 */
public interface HeaderFormatter 
{
    /**
     * This method returns the headers.
     * @return The headers.
     */
    public TreeMap<String,String> GetHeaders();

    /**
     * This method adds a header to the request.
     * @param key The key of the header.
     * @param value The value of the header.
     */
    public void AddHeader(String key, String value);
}
