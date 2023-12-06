package group3191.utils;

import group3191.utils.requests.Request;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Sends API requests to OpenWeather API
 */
public class RestClient {
    Request request;
    
    /**
     * Constructor for the RestClient class.
     * @param request The request to be sent.
     */
    public RestClient(Request request) {
        this.request = request;
    }
    
    /**
     * This method sends the request and returns the response.
     * @return The response of the request.
     */
    public HttpResponse<String> getResponse() {
        HttpResponse<String> response = null;
        try {
            var client = HttpClient.newHttpClient();

            HttpRequest.Builder httpBuilder = HttpRequest.newBuilder(URI.create(request.GetURI()));
            var headersArr = request.GetHeaders();
            if (headersArr.size() > 1){
                for (var header : headersArr.entrySet()) {
                    httpBuilder.header(header.getKey(), header.getValue());
                }
            }
            var httpReq = httpBuilder.GET().build();

            response = client.send(httpReq, BodyHandlers.ofString());
            System.out.println(response);
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return response;
    }
}




