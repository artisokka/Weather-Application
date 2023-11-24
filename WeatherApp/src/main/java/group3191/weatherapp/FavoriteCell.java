/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp;

/**
 *
 * @author EeroP
 */
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import group3191.models.GeoName;
import javafx.scene.control.ListView;

public class FavoriteCell extends ListCell<GeoName> {
    private final Button favoriteButton;

    public FavoriteCell() {
        super();
        favoriteButton = new Button();
        favoriteButton.setGraphic(new ImageView("/icons/fav_icon.png")); // Replace with your favorite icon
        favoriteButton.setOnAction(event -> {
            GeoName selectedItem = getItem();
            if (selectedItem != null) {
                // Handle adding as favorite for 'selectedItem'
                // Example: Pass 'selectedItem' to add as a favorite
                addToFavorites(selectedItem);
            }
        });
    }

    @Override
    protected void updateItem(GeoName item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            setGraphic(favoriteButton);
            // Set other text properties for the list cell based on 'item'
            setText(item.getCityName()); // Example: Display the name in the list cell
        }
    }

    // Method to handle adding a location as a favorite
    private void addToFavorites(GeoName location) {
        // Logic to add 'location' as a favorite
        // Example: Pass 'location' to the method to add it as a favorite
        //WeatherWindowController.setFavoriteLocation(location);
    }

    // Factory method to create FavoriteCell instances
    public static Callback<ListView<GeoName>, ListCell<GeoName>> getFavoriteCellFactory() {
        return listView -> new FavoriteCell();
    }
}
