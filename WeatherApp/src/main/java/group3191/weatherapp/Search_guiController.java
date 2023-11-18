/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import com.google.gson.Gson;
import group3191.utils.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author EeroP
 */



public class Search_guiController {

    @FXML
    private Label locationLabel;

    @FXML
    private TextField locationSearchTextField;

    @FXML
    private Label resultsLabel;

    @FXML
    private ListView<?> resultsListView;

    @FXML
    void searchTextFieldOnAction(ActionEvent event) {
        String textContent = this.locationSearchTextField.getText();
        if(!textContent.isEmpty() && !textContent.isBlank())
        {
            System.out.println("testing");
            this.testLocationApi(textContent);
        } else
        {
            System.out.println("textfield contents: " + textContent);
        }

    }
    
    private void testLocationApi(String location)
    {
        
        Api api = new Api();
        Gson gson = new Gson();
        String responseBody = api.lookUpLocation(location);
        System.out.println(responseBody);
        
        //WeatherDTO weatherDTO = gson.fromJson(responseBody, WeatherDTO.class);
        //return weatherDTO;
    }

}

