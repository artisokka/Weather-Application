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
import group3191.models.weather.DailyWeatherDTO;
import group3191.models.weather.WeatherDTO;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import group3191.weatherapp.gui_utilities.VisualizationUtilities;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateCurrentWeatherDisplay;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateDayForecastDisplay;
import static group3191.weatherapp.gui_utilities.VisualizationUtilities.generateHourForecastDisplay;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author EeroP
 */
public class Weatherapp_guiController implements Initializable {

    private WeatherDTO currentWeather;
    private GeoName currentLocation;

    private List<WeatherDTO> hourlyForecast;
    private List<DailyWeatherDTO> dailyForecast;

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
    private ComboBox<String> unitSystemComboBox;
    
    @FXML
    private Label unitSystemLabel;


    @FXML
    private ScrollPane weatherDayScrolllPane;

    @FXML
    private ScrollPane weatherHourScrollPane;
    
    @FXML
    private ScrollPane weatherTopScrollPane;

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
         
        List<WeatherDTO> forecastHourly = weatherController.getForecast(location.getLat(), location.getLng());
        
        List<DailyWeatherDTO> forecastDaily = weatherController.getDailyForecast(location.getLat(), location.getLng());
        
        this.hourlyForecast = forecastHourly;
        this.dailyForecast = forecastDaily;

    }
    
    @FXML
    void unitChanged(ActionEvent event) {
        this.showWeather();

    }

    void showWeather() {
        System.out.println("City: " + this.currentWeather.getName());
        System.out.println("Temperature: " + this.currentWeather.getMain().getTemp());

        String locationText
                = String.format("%s (%s)",
                        this.currentLocation.getCityName(),
                        this.currentWeather.getName());

        this.locationLabel.setText(locationText);

        generateCurrentWeatherDisplay(this.currentWeather, this.weatherTopScrollPane);
        generateDayForecastDisplay(this.dailyForecast, this.weatherDayScrolllPane);
        boolean useOneHour = false;
        generateHourForecastDisplay(this.hourlyForecast, this.weatherHourScrollPane, useOneHour);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showLast();
        ArrayList<String> allUnits = new ArrayList();
        allUnits.add("Metric");
        allUnits.add("Imperial");
        ObservableList<String> units = FXCollections.observableArrayList(allUnits);
        this.unitSystemComboBox.setItems(units);
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
