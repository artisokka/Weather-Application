package testfx.Test;

import group3191.weatherapp.WeatherApp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;

public class GUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        try {
            new WeatherApp().startTest(stage);
        } catch (IOException e) {
            System.out.println("Test failed!");
        }
    }

    @Test
    public void testSearchAndFavoritesButton() {
        clickOn("#searchButton");
        FxAssert.verifyThat("#resultsGridPane", isVisible());
    }

    @Test
    public void testRefreshButton() {
        clickOn("#refreshButton");
        FxAssert.verifyThat("#weatherTop", isVisible());
    }

    @Test
    public void testUnitSystemComboBox() {
        String boxID = "#unitSystemComboBox";

        
        clickOn("#settingsTab");
        
        String initialValue = lookup(boxID).queryAs(
                ComboBox.class).
                getSelectionModel().getSelectedItem().toString();

        // Choose the appropriate key based on the currently saved setting
        KeyCode keyToPress = initialValue.equals("Metric") ? KeyCode.DOWN
                : KeyCode.UP;

        clickOn(boxID).type(keyToPress).type(KeyCode.ENTER);
        WaitForAsyncUtils.waitForFxEvents();

        String newValue = lookup(boxID).queryAs(ComboBox.class).
                getSelectionModel().getSelectedItem().toString();

        assert !initialValue.equals(newValue);

    }

    @Test
    public void testWeatherScrollPane() {
        FxRobot robot = new FxRobot();
        String name = "#weatherDayScrolllPane";
        ScrollPane scrollpane = robot.lookup(name).queryAs(ScrollPane.class);
        verifyThat(scrollpane, isVisible());
    }

    @Test
    public void testSearchFunction() {
        clickOn("#searchButton");
        var resultView = lookup("#resultsGridPane");
        clickOn("#locationSearchTextField").write("Helsinki").
                type(KeyCode.ENTER);

        var newResultView = lookup("#resultsGridPane");

        assert !resultView.equals(newResultView);
    }

    @AfterAll
    public static void close() {
        try {
            String fileName = "test_db.json";
            Path path = Paths.get(fileName);

            Files.delete(path);

            System.out.println("File removed: " + fileName);
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }
}
