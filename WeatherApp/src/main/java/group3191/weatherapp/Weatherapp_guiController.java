/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import com.google.gson.Gson;
import group3191.controllers.WeatherController;
import group3191.db.DatabaseHandler;
import group3191.utils.Api;
import java.net.URL;
import java.util.List;
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
import group3191.models.geo.GeoName;
import group3191.models.weather.WeatherDTO;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import group3191.weatherapp.gui_utilities.VisualizationUtilities;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateCurrentWeatherDisplay;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateDayForecastDisplay;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateHourForecastDisplay;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author EeroP
 */
public class Weatherapp_guiController implements Initializable {

    private WeatherDTO currentWeather;
    private GeoName currentLocation;

    private List<WeatherDTO> currentForecast;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Menu fileMenu;

    @FXML
    private Label locationLabel;

    @FXML
    private Label favoritesLabel;

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
    private GridPane weatherTopGrid;

    @FXML
    private ScrollPane weatherDayScrolllPane;

    @FXML
    private ScrollPane weatherHourScrollPane;

    private GeoName defaultWeather = new GeoName("Tampere", "Finland", 61.4991, 23.7871);

    @FXML
    void onAction(ActionEvent event) {

    }

    @FXML
    void refreshOnAction(ActionEvent event) {

    }

    @FXML
    void searchAndFauvoritesOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_gui.fxml"));
        try {
            Parent root = loader.load();

            // Get the controller for the search window
            Search_guiController controller = loader.getController();
            MainController mainCont = new MainController();
            mainCont.setWeatherController(this);
            controller.setMainController(mainCont);

            // Create a new stage for the search window
            Stage searchStage = new Stage();
            searchStage.setTitle("Search Window");
            searchStage.setScene(new Scene(root));

            // Show the search window
            searchStage.show();
        } catch (Exception error) {
            System.out.println("error in searchAndFauvorites eventhandler: " + error);
        }

    }

    void setWeather(GeoName location) {
        WeatherController weatherController = new WeatherController();
        WeatherDTO weatherDTO = weatherController.getCurrentWeather(location.getLat(), location.getLng());

        System.out.println("weatherDTO: " + weatherDTO);
        this.currentLocation = location;
        this.currentWeather = weatherDTO;
        //System.out.println("forecast is: "+ weatherController.getForecast(location.getLat(), location.getLng())); 
        List<WeatherDTO> forecastOfSomething = weatherController.getForecast(location.getLat(), location.getLng());
        this.currentForecast = forecastOfSomething;

    }

    void showWeather() {
        System.out.println("City: " + this.currentWeather.getName());
        System.out.println("Temperature: " + this.currentWeather.getMain().getTemp());

        String locationText
                = String.format("%s (%s)",
                        this.currentLocation.getCityName(),
                        this.currentWeather.getName());

        this.locationLabel.setText(locationText);

//        int visibility = this.currentWeather.getVisibility();
//        group3191.models.weather.Main main = this.currentWeather.getMain();
//        double temperature = main.getTemp();
//        group3191.models.weather.Wind wind = this.currentWeather.getWind();
//        
//        Label visibilityLabel = new Label("Visibility: " + visibility + " m");
//        Label temperatureLabel = new Label("Temperature: " + temperature + " °C");
//        Label windSpeedLabel = new Label("Wind Speed: " + wind.getSpeed() + " m/s");
//        Label windDegreeLabel = new Label("Wind Degree: " + wind.getDeg() + "°");
//        Label windGustLabel = new Label("Wind Gust: " + wind.getGust() + " m/s");
//        
//        //this.weatherTop.getChildren().clear();
//        VBox vbox = new VBox(10);
//        
//        
//        vbox.getChildren().addAll(visibilityLabel, temperatureLabel,
//                                         windSpeedLabel, windDegreeLabel,
//                                         windGustLabel);
        //this.weatherTop.getChildren().add(vbox);
        generateCurrentWeatherDisplay(this.currentWeather, this.weatherTopGrid);
        generateDayForecastDisplay(this.currentForecast, this.weatherDayScrolllPane);
        generateHourForecastDisplay(this.currentForecast, this.weatherHourScrollPane);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showLast();
        // TODO
        //this.currentWeather = this.testWeatherAPI();
        //testGetForecast();
    }

    private void showLast() {
        Platform.runLater(() -> {
            GeoName last = DatabaseHandler.getLastSearch();
            this.currentLocation = last != null ? last : defaultWeather;
            System.out.println("currentLocation: " + currentLocation);
            this.setWeather(this.currentLocation);
            this.showWeather();
        });
    }

}
