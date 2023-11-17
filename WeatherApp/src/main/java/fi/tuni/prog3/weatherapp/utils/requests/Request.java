package fi.tuni.prog3.weatherapp.utils.requests;

import java.util.TreeMap;

import fi.tuni.prog3.weatherapp.utils.formatters.HeaderFormatter;
import fi.tuni.prog3.weatherapp.utils.formatters.URIFormatter;

/**
 * This class is used to create a request.
 */
public abstract class Request {

    protected URIFormatter uriFormat;
    protected HeaderFormatter hFormat;
    protected String endpoint;

    public Request(RequestParameters URIParameters, RequestParameters HeaderParameters, String endpoint) {
        this.endpoint = endpoint;
        CreateURIFormatter(URIParameters);
        CreateHeaderFormatter(HeaderParameters);
    }

    public abstract void CreateURIFormatter(RequestParameters URIParameters);
    public abstract void CreateHeaderFormatter(RequestParameters headerParameters);

    public String GetURI() {
        return uriFormat.GetURI();
    }

    public TreeMap<String,String> GetHeaders() {
        return hFormat.GetHeaders();
    }
}
