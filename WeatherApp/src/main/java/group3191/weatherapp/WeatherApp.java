/**
 * The `group3191.weatherapp` package contains classes for a weather application.
 */
package group3191.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import group3191.db.DatabaseHandler;
import group3191.utils.Settings;

/**
 * JavaFX WeatherApp
 */
public class WeatherApp extends Application {

    private static Scene scene;

    /**
     * Initializes the application and sets up the main stage.
     *
     * @param stage The main stage of the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Settings.Init();
        DatabaseHandler.Init();
        scene = new Scene(loadFXML("weatherapp_gui"), 500, 700);
        stage.setScene(scene);
        stage.setTitle("Weather Application");
        stage.show();
    }

    public void startTest(Stage stage) throws IOException {
        Settings.InitTest();
        DatabaseHandler.Init();
        scene = new Scene(loadFXML("weatherapp_gui"), 500, 700);
        stage.setScene(scene);
        stage.setTitle("Weather Application");
        stage.show();
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     *
     * @param fxml The name of the FXML file.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads the specified FXML file and returns the root node.
     *
     * @param fxml The name of the FXML file.
     * @return The root node of the loaded FXML file.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

}