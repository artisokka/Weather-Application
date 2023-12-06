/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package group3191.utils.formatters;

/**
 * This class is used to format a URI for the OpenWeatherMap API.
 */
public class OpenWeatherFormatter extends Formatter {

    public OpenWeatherFormatter(String URI, String endpoint, String key) {
        super(URI, endpoint, key);
    }
    @Override
    public void AddParameter(String key, String value) {
        keyValues.put(key, value);
    }

    @Override
    public String GetURI() {
        StringBuilder sb = new StringBuilder();
        sb.append(URI);
        sb.append(endpoint);
        sb.append("?appid=");
        sb.append(key);
        //sb.append("&units=metric");
        for(var key : keyValues.keySet()) {
            sb.append(String.format("&%s=%s", key, keyValues.get(key)));
        }
        return sb.toString();
    }

    @Override
    public String GetBaseURI() {
        return URI;
    }
}
