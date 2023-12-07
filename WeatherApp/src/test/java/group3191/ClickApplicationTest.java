package group3191;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import group3191.weatherapp.WeatherApp;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClickApplicationTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        try {
            new WeatherApp().start(stage);
        } catch (IOException e) {
            System.out.println("Test failed!");
        }
    }

    @Test
    @DisplayName("Testing button basics")
    public void checkButtons() {
        Button node = fromAll().lookup("#refreshButton").query();
        assertEquals(node.getText(), "Refresh weather");
    }
}