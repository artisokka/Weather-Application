package junit.Test;

import group3191.controllers.WeatherController;
import group3191.models.WeatherModel;
import group3191.weatherapp.Weatherapp_guiController.*;
import group3191.models.geo.GeoName;
import group3191.weatherapp.Weatherapp_guiController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
