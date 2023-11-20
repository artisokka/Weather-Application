/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import com.google.gson.Gson;
import group3191.models.GeoNamesResponse;
import group3191.utils.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import group3191.utils.ApiHandlerGeoNames;
import group3191.models.GeoName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private ListView<GeoName> resultsListView;

    @FXML
    void searchTextFieldOnAction(ActionEvent event) {
        String textContent = this.locationSearchTextField.getText();
        if (!textContent.isEmpty() && !textContent.isBlank()) {
            System.out.println("testing");
            GeoNamesResponse result = ApiHandlerGeoNames.getLocationSuggestions(textContent);
            if (result != null) {
                ObservableList<GeoName> observableGeoNames = FXCollections.observableArrayList(result.getGeoNames());
                this.resultsListView.setItems(observableGeoNames);
                
                
//                for (int i = 0; i < result.getSize(); i++) {
//                    System.out.println("result_"+i+" " + result.getIndex(i));
//                }
            }

            //this.testLocationApi(textContent);
        }

    }

    private void testLocationApi(String location) {

        Api api = new Api();
        Gson gson = new Gson();
        String responseBody = api.lookUpLocation(location);
        System.out.println(responseBody);

        //WeatherDTO weatherDTO = gson.fromJson(responseBody, WeatherDTO.class);
        //return weatherDTO;
    }

}
