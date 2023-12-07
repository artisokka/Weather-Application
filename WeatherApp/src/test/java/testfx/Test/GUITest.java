package testfx.test;

import group3191.weatherapp.WeatherApp;

import java.awt.Image;
import java.io.IOException;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import org.testfx.util.WaitForAsyncUtils;

public class GUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        try {
            new WeatherApp().start(stage);
        } catch (IOException e) {
            System.out.println("Test failed!");
        }
    }

    @Test
    public void testSearchAndFavoritesButton() {
        clickOn("#searchButton");
        // TODO
    }

    @Test
    public void testRefreshButton() {
        clickOn("#refreshButton");
        // TODO
    }

    @Test
    public void testUnitSystemComboBox() {
        String boxID = "#unitSystemComboBox";

        clickOn("#settingsTab");
        ComboBox<?> comboBox = lookup(boxID).query();

        String initialValue = lookup(boxID).queryAs(
                ComboBox.class).
                getSelectionModel().getSelectedItem().toString();

        // Choose the appropriate key based on the current setting
        KeyCode keyToPress = initialValue.equals("Metric") ? KeyCode.DOWN
                : KeyCode.UP;

        clickOn(boxID).type(keyToPress).type(KeyCode.ENTER);
        WaitForAsyncUtils.waitForFxEvents();

        String newValue = lookup(boxID).queryAs(ComboBox.class).
                getSelectionModel().getSelectedItem().toString();

        assert !initialValue.equals(newValue);

    }

    @Test
    public void testWeatherTabs() {
        FxRobot robot = new FxRobot();
        clickOn("#settingsTab");
        Node settingsTab = robot.lookup("#settingsTab").query();
        assertTrue(settingsTab.isFocused());

        clickOn("#weatherTab");
        Node weatherTab = robot.lookup("#weatherTab").query();
        assertTrue(weatherTab.isFocused());
    }

    @Test
    public void testWeatherScrollPaneInteractions() {
        FxRobot robot = new FxRobot();
        String scrollpane = "#weatherHourScrollPane";
        verifyThat(scrollpane, isVisible());

        double initialVvalue = lookup(scrollpane).queryAs(
                ScrollPane.class).getHvalue();

        robot.scroll(5, HorizontalDirection.RIGHT);

        double newVvalue = lookup(scrollpane).queryAs(
                ScrollPane.class).getHvalue();
        assert newVvalue > initialVvalue;
    }

    @Test
    public void testSearchAndFavoritesButtonInteraction() {
        clickOn("#searchButton");
        var resultView = lookup("resultsGridPane");
        clickOn("#locationSearchTextField").write("Helsinki").
                type(KeyCode.ENTER);

        var newResultView = lookup("resultsGridPane");

        assert !resultView.equals(newResultView);
    }

    public void testWeatherIconUpdate() {
        ImageView weatherIcon = lookup("#weatherIcon").query();
        ImageView expectedImage = new ImageView(
                "/path/to/expected_weather_icon.png");

        // Verify that the actual image is the same as the expected image
        assertEquals(weatherIcon, expectedImage);
    }
}
