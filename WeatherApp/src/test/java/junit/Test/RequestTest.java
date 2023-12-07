package junit.Test;

import org.junit.jupiter.api.Test;
import group3191.utils.Settings;
import group3191.utils.formatters.OpenWeatherFormatter;
import group3191.utils.requests.OpenWeatherRequest;
import group3191.utils.requests.RequestParameters;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class RequestTest {

    @BeforeAll
    public static void initSettings() {
        Settings.InitTest();
    }

    @Test
    public void testCreateURIFormatter() {
        RequestParameters uriParameters = new RequestParameters();
        String endpoint = "testEndpoint";
        OpenWeatherRequest openWeatherRequest = new OpenWeatherRequest(
                Settings.OPENWEATHER_URI,
                uriParameters, endpoint);
        openWeatherRequest.CreateURIFormatter(uriParameters);

        assertNotNull(openWeatherRequest);
        assertTrue(openWeatherRequest instanceof OpenWeatherRequest);

        OpenWeatherFormatter formatter = new OpenWeatherFormatter(
                Settings.OPENWEATHER_URI,
                endpoint, Settings.GetValue("API_KEY"));

        assertEquals(Settings.OPENWEATHER_URI, formatter.GetBaseURI());

    }

    @Test
    public void createHeaderFormatter() {
        RequestParameters headerParameters = new RequestParameters();
        OpenWeatherRequest openWeatherRequest = new OpenWeatherRequest(
                Settings.OPENWEATHER_URI,
                new RequestParameters(), "testEndpoint");
        openWeatherRequest.CreateHeaderFormatter(headerParameters);

        assertNotNull(headerParameters);
        assertNotNull(openWeatherRequest.GetHeaders());
    }
}
