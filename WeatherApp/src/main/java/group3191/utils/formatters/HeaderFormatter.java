package group3191.utils.formatters;

import java.util.TreeMap;

/**
 * This interface is used to format headers.
 */
public interface HeaderFormatter 
{
    public TreeMap<String,String> GetHeaders();

    public void AddHeader(String key, String value);
}
