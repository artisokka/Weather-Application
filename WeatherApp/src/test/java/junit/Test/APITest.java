package junit.Test;

import group3191.models.weather.*;
import group3191.controllers.*;
import group3191.utils.Settings;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class APITest {

    @BeforeAll
    public static void initSettings() {
        Settings.InitTest();
    }

    @Test
    public void testGetCurrentWeather() {

        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.getCurrentWeather(61.49911,
                23.78712);
        assertNotNull(weatherDTO);
        assertEquals("Tampere", weatherDTO.getName());
    }

    @Test
    public void testGetForecast() {
        WeatherController weatherController = new WeatherController();
        List<WeatherDTO> weatherDTOlist = weatherController.
                getForecast(61.4991,
                        23.7871);
        assertNotNull(weatherDTOlist);
    }

    @Test
    public void testLookUpLocation() {
        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.lookUpLocation("Lieksa");
        assertNotNull(weatherDTO);
        assertEquals("Lieksa", weatherDTO.getName());
    }
}
