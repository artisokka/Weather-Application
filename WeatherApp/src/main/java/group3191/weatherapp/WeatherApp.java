package group3191.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import group3191.controllers.WeatherController;
import group3191.models.WeatherDTO;
import group3191.utils.Settings;

/**
 * JavaFX WeatherApp
 */
public class WeatherApp extends Application {

    private static Scene scene;
    
    
    
    
    
    

    @Override
    public void start(Stage stage) throws IOException {
        Settings.Init();
        scene = new Scene(loadFXML("weatherapp_gui"), 500, 700);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}