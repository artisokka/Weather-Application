/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import group3191.controllers.WeatherController;
import group3191.models.WeatherDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EeroP
 */
public class Weatherapp_guiController implements Initializable {

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Menu fileMenu;

    @FXML
    private Label locationLabel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private Button refreshButton;

    @FXML
    private Button searchButton;

    @FXML
    private Tab settingsTab;

    @FXML
    private AnchorPane weatherAllPane;

    @FXML
    private AnchorPane weatherBottom;

    @FXML
    private AnchorPane weatherMiddle;

    @FXML
    private Tab weatherTab;

    @FXML
    private AnchorPane weatherTop;

    @FXML
    void onAction(ActionEvent event) {

    }

    @FXML
    void refreshOnAction(ActionEvent event) {
        WeatherDTO weather = this.testWeatherAPI();

    }

    @FXML
    void searchAndFauvoritesOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_gui.fxml"));
        try
        {
            Parent root = loader.load();

            // Get the controller for the search window
            Search_guiController controller = loader.getController();

            // Create a new stage for the search window
            Stage searchStage = new Stage();
            searchStage.setTitle("Search Window");
            searchStage.setScene(new Scene(root));

            // Show the search window
            searchStage.show();
        } catch(Exception error)
        {
            System.out.println("error in searchAndFauvorites eventhandler: "+error);
        }
            

    }

    private WeatherDTO testWeatherAPI() {
        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.getCurrentWeather(61.4978, 23.7610);
        System.out.println("City: " + weatherDTO.getName());
        System.out.println("Temperature: " + weatherDTO.getMain().getTemp());
        return weatherDTO;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
