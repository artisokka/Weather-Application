package group3191.utils.requests;

import group3191.utils.formatters.DefaultHeaderFormatter;
import group3191.utils.Settings;
import group3191.utils.formatters.OpenWeatherFormatter;

/**
 * This class represents a request to OpenWeather API
 */
public class OpenWeatherRequest extends Request {
    public OpenWeatherRequest(String uri, RequestParameters URIParameters, String endpoint) {
        super(uri, URIParameters, new RequestParameters(), endpoint);
    }

    @Override
    public void CreateURIFormatter(RequestParameters params) {
        OpenWeatherFormatter uriFormat = new OpenWeatherFormatter(uri,
                                         endpoint, Settings.GetValue("API_KEY"));

        for (var key : params.GetParameters().keySet()) {
            uriFormat.AddParameter(key, params.Get(key));
        }

        this.uriFormat = uriFormat;
    }

    @Override
    public void CreateHeaderFormatter(RequestParameters headerParameters) {
        this.hFormat = new DefaultHeaderFormatter();
    }

    
}
