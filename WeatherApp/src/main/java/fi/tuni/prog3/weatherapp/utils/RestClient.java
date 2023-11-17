/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package fi.tuni.prog3.weatherapp.utils;
import fi.tuni.prog3.weatherapp.utils.requests.Request;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 *
 * @author dmmile
 */
public class RestClient {
    Request request;
    
    public RestClient(Request request) {
        this.request = request;
    }
    
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




