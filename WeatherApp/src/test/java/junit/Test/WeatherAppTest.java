package junit.Test;

import group3191.models.weather.*;
import group3191.controllers.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppTest {

    public WeatherAppTest() {

    }

    @Test
    public void testWeatherAPI() {
        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.getCurrentWeather(61.4978,
                23.7610);
        assertNotNull(this);
        assertEquals("Tampere", weatherDTO.getName());
    }

    @Test
    public void testGetCurrentWeather() {

    }

    @Test
    public void testGetForecast() {
        WeatherController weatherController = new WeatherController();
        List<WeatherDTO> weatherDTOlist = weatherController.getForecast(61.4978,
                23.7610);
        assertNotNull(this);
        assertEquals("Tampere", weatherDTOlist.get(0).getName());
    }

    @Test
    public void testLookUpLocation() {

    }

}
