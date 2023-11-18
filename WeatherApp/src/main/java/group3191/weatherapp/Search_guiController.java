/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

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

    }

}

