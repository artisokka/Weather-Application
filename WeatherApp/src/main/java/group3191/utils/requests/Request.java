package group3191.utils.requests;

import java.util.TreeMap;

import group3191.utils.formatters.HeaderFormatter;
import group3191.utils.formatters.URIFormatter;

/**
 * This class is used to create a request.
 */
public abstract class Request {

    protected URIFormatter uriFormat;
    protected HeaderFormatter hFormat;
    protected String endpoint;
    protected String uri;

    /**
     * Constructor for the Request class.
     * @param uri The URI of the request.
     * @param URIParameters The parameters of the request.
     * @param HeaderParameters The header parameters of the request.
     * @param endpoint The endpoint of the request.
     */
    public Request(String uri, RequestParameters URIParameters, RequestParameters HeaderParameters, String endpoint) {
        this.endpoint = endpoint;
        this.uri = uri;
        CreateURIFormatter(URIParameters);
        CreateHeaderFormatter(HeaderParameters);
    }

    /**
     * This method creates the URI formatter for the request.
     * @param URIParameter The parameters of the request.
     */
    public abstract void CreateURIFormatter(RequestParameters URIParameter);
    /**
     * This method creates the header formatter for the request.
     * @param headerParameters The header parameters of the request.
     */
    public abstract void CreateHeaderFormatter(RequestParameters headerParameters);

    /**
     * This method returns the URI of the request.
     * @return The URI of the request.
     */
    public String GetURI() {
        return uriFormat.GetURI();
    }

    /**
     * This method returns the headers of the request.
     * @return The headers of the request.
     */
    public TreeMap<String,String> GetHeaders() {
        return hFormat.GetHeaders();
    }
}
