package junit.Test;

import group3191.utils.Settings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest {

    @BeforeAll
    public static void initSettings() {
        Settings.InitTest();
    }

    @Test
    public void testEnvKey() {
        String key = Settings.GetValue("API_KEY");
        assertNotNull(key);
    }

    @Test
    public void testEnvProAPI() {
        String pro_api = Settings.GetValue("USE_PRO_API");
        assertNotNull(pro_api);
    }

    @Test
    public void testEnvOpenWeather() {
        String openweather = Settings.OPENWEATHER_URI;
        assertNotNull(openweather);
    }

    @Test
    public void testEnvGeoNames() {
        String geonames = Settings.GetValue("GEONAMES_USERNAME");
        assertNotNull(geonames);
    }
}
