package fi.tuni.prog3.weatherapp.utils.requests;

import fi.tuni.prog3.weatherapp.utils.formatters.DefaultHeaderFormatter;
import fi.tuni.prog3.weatherapp.utils.Settings;
import fi.tuni.prog3.weatherapp.utils.formatters.OpenWeatherFormatter;

/**
 * This class represents a request to OpenWeather API
 */
public class OpenWeatherRequest extends Request {
    public OpenWeatherRequest(RequestParameters URIParameters, String endpoint) {
        super(URIParameters, new RequestParameters(), endpoint);
    }

    @Override
    public void CreateURIFormatter(RequestParameters params) {
        OpenWeatherFormatter uriFormat = new OpenWeatherFormatter(Settings.GetValue("OPENWEATHER_URI", "https://api.openweathermap.org/data/2.5/"),
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
