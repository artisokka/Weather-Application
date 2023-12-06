package junit.Test;

import group3191.models.weather.AirQuality;
import group3191.models.weather.Clouds;
import group3191.models.weather.Coord;
import group3191.models.weather.Main;
import group3191.models.weather.Sys;
import group3191.models.weather.Weather;
import group3191.models.weather.WeatherDTO;
import group3191.models.weather.Wind;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WeatherDTOTest {

    @Test
    public void testGetAndSetCoord() {

        WeatherDTO weatherDTO = new WeatherDTO();
        Coord coord = new Coord();
        coord.setLat(10.0);
        coord.setLon(20.0);

        weatherDTO.setCoord(coord);
        var retrievedCoord = weatherDTO.getCoord();

        assertEquals(coord, retrievedCoord);
    }

    @Test
    public void testGetAndSetWeather() {
        WeatherDTO weatherDTO = new WeatherDTO();
        Weather weather = new Weather();
        weather.setDescription("Windy");

        weatherDTO.setWeather(List.of(weather));
        var retrievedWeather = weatherDTO.getWeather();

        assertNotNull(retrievedWeather);
        assertEquals(1, retrievedWeather.size());
        assertEquals(weather, retrievedWeather.get(0));
    }

    @Test
    public void testGetAndSetBase() {
        WeatherDTO weatherDTO = new WeatherDTO();
        String input = "test base";
        weatherDTO.setBase(input);

        var retrieved = weatherDTO.getBase();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetMain() {
        WeatherDTO weatherDTO = new WeatherDTO();
        Main input = new Main();
        input.setFeels_like(10);
        input.setHumidity(1);
        input.setPressure(0);
        input.setTemp(10);
        input.setTemp_max(30);
        input.setTemp_min(-30);

        weatherDTO.setMain(input);

        var retrieved = weatherDTO.getMain();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetVisibility() {
        WeatherDTO weatherDTO = new WeatherDTO();
        int input = 1;
        weatherDTO.setVisibility(input);

        var retrieved = weatherDTO.getVisibility();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetWind() {
        WeatherDTO weatherDTO = new WeatherDTO();
        Wind input = new Wind();
        input.setDeg(1);
        input.setGust(2.0);
        input.setSpeed(10);

        weatherDTO.setWind(input);

        var retrieved = weatherDTO.getWind();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetClouds() {
        WeatherDTO weatherDTO = new WeatherDTO();
        Clouds input = new Clouds();
        input.setAll(1);

        weatherDTO.setClouds(input);

        var retrieved = weatherDTO.getClouds();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetDt() {
        WeatherDTO weatherDTO = new WeatherDTO();
        long input = 0;
        weatherDTO.setDt(input);

        var retrieved = weatherDTO.getDt();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetSys() {
        WeatherDTO weatherDTO = new WeatherDTO();
        Sys input = new Sys();
        input.setCountry("Suomi");
        input.setId(1);
        input.setSunrise(0);
        input.setSunset(1);
        input.setType(1);
        weatherDTO.setSys(input);

        var retrieved = weatherDTO.getSys();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetID() {
        WeatherDTO weatherDTO = new WeatherDTO();
        int input = 1;
        weatherDTO.setId(input);

        var retrieved = weatherDTO.getId();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetName() {
        WeatherDTO weatherDTO = new WeatherDTO();
        String input = "testinimi";
        weatherDTO.setName(input);

        var retrieved = weatherDTO.getName();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetCod() {
        WeatherDTO weatherDTO = new WeatherDTO();
        int input = 1;
        weatherDTO.setCod(input);

        var retrieved = weatherDTO.getCod();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testGetAndSetAirQuality() {
        WeatherDTO weatherDTO = new WeatherDTO();
        AirQuality input = AirQuality.GOOD;
        weatherDTO.setAirQuality(input);

        var retrieved = weatherDTO.getAirQuality();

        assertNotNull(weatherDTO);
        assertEquals(input, retrieved);
    }

    @Test
    public void testToString() {

        WeatherDTO weatherDTO = new WeatherDTO();
        Coord coord = new Coord();
        coord.setLat(10.0);
        coord.setLon(20.0);

        weatherDTO.setCoord(coord);

        String result = weatherDTO.toString();

        assertNotNull(result);
        assertTrue(result.contains("coord=" + coord));
    }
}
