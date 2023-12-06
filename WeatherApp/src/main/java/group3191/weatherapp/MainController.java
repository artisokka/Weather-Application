/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp;

/**
 * The MainController class is responsible for managing the main functionality of the WeatherApp.
 * It handles the communication between the GUI and the weather controller.
 * 
 * @author EeroP
 */
public class MainController {
    private Weatherapp_guiController weatherController;

    /**
     * Sets the weather controller for the MainController.
     * 
     * @param controller The weather controller to be set.
     */
    public void setWeatherController(Weatherapp_guiController controller) {
        this.weatherController = controller;
    }

    /**
     * Returns the weather controller associated with the MainController.
     * 
     * @return The weather controller.
     */
    public Weatherapp_guiController getWeatherController() {
        return weatherController;
    }

}
