package junit.Test;

import group3191.utils.Settings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest {

    @Test
    public void testSettings() {
        String key = Settings.GetValue("API_KEY");
        String pro_api = Settings.GetValue("USE_PRO_API");
        String openweather = Settings.GetValue("OPENWEATHER_URI");
        String debug = Settings.GetValue("DEBUG");
        String geonames = Settings.GetValue("GEONAMES_USERNAME");

        assertNotNull(key);
        assertNotNull(pro_api);
        assertNotNull(openweather);
        assertNotNull(debug);
        assertNotNull(geonames);
    }
}
