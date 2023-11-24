/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp;

/**
 *
 * @author EeroP
 */
public class MainController {
    private Weatherapp_guiController weatherController;

    public void setWeatherController(Weatherapp_guiController controller) {
        this.weatherController = controller;
    }

    public Weatherapp_guiController getWeatherController() {
        return weatherController;
    }

}
