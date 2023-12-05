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

    @BeforeAll
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
        GeoName location = new GeoName("TestCity", "TestCountry", 0.0, 0.0);

        assertEquals(location, controller.lookUpLocation(location.getCityName()));
        assertNotNull(controller.getCurrentWeather(0.0, 0.0));
    }

}
