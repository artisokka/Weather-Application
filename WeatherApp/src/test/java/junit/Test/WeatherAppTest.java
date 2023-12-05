package junit.Test;

import group3191.models.weather.*;
import group3191.controllers.*;
import group3191.utils.Settings.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherAppTest {

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
                getForecast(61.49911,
                        23.78712);
        assertNotNull(weatherDTOlist);
        assertEquals("Tampere", weatherDTOlist.get(0).getName());
    }

    @Test
    public void testLookUpLocation() {
        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.lookUpLocation("Lieksa");
        assertNotNull(weatherDTO);
        System.out.println(weatherDTO);
        //assertEquals("Lieksa", weatherDTO.getName());
    }

}
