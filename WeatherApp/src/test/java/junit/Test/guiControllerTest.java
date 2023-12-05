package junit.Test;

import group3191.controllers.WeatherController;
import group3191.models.WeatherModel;
import group3191.models.geo.GeoName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class guiControllerTest {

    private WeatherController controller;

    @Test
    public void setUp() {
        controller = new WeatherController();
        assertNotNull(controller);
    }

    @Test
    public void testWeatherController() {
        WeatherModel weatherModel = new WeatherModel();
        assertNotNull(weatherModel);
    }

    @Test
    public void testGeoName() {
        controller = new WeatherController();
        GeoName location = new GeoName("TestCity", "TestCountry", 1.0, 1.0);

        assertEquals("TestCity", location.getCityName());
        assertNotNull(location);
    }

}
