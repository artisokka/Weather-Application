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

import group3191.models.geo.GeoName;
import java.util.function.Consumer;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class FavoriteCell extends ListCell<GeoName> {

    private final Button favoriteButton;
    private final Consumer<GeoName> addToFavoritesHandler;
    private final Consumer<GeoName> removeFromFavoritesHandler;

    private boolean isFavorite;

    private ListView<GeoName> favoritesListView;

    public FavoriteCell(Consumer<GeoName> addToFavoritesHandler, Consumer<GeoName> removeFromFavoritesHandler) {
        super();
         this.addToFavoritesHandler = addToFavoritesHandler;
        this.removeFromFavoritesHandler = removeFromFavoritesHandler;
        favoriteButton = new Button();
        isFavorite = false;
        favoriteButton.setGraphic(new ImageView("/icons/fav_icon_empty.png")); // Replace with your favorite icon
        favoriteButton.setOnAction(event -> {
            GeoName selectedItem = getItem();
            if (selectedItem != null) {
                // Handle adding as favorite for 'selectedItem'
                // Example: Pass 'selectedItem' to add as a favorite
                //addToFavorites(selectedItem);
                //addToFavoritesHandler.addToFavorites(selectedItem);
                if (isFavorite) {
                    removeFromFavoritesHandler.accept(selectedItem);
                    isFavorite = false;

                } else {
                    addToFavoritesHandler.accept(selectedItem);
                    isFavorite = true;
                    updateButtonIcon();
                }

            }
        });
    }

    private void updateButtonIcon() {
        String iconPath = isFavorite ? "/icons/fav_icon_full.png" : "/icons/fav_icon_empty.png";
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        favoriteButton.setGraphic(new ImageView(icon));
    }

    public void setFavoritesListView(ListView<GeoName> favoritesListView) {
        this.favoritesListView = favoritesListView;
    }

    @Override
    protected void updateItem(GeoName item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            setGraphic(favoriteButton);
            // Set other text properties for the list cell based on 'item'
            String textToShow = String.format("%s, %s, lat=%s, long=%s",
                    item.getCityName(), item.getCountryName(),
                    item.getLat(), item.getLng());
            setText(textToShow); // Example: Display the name in the list cell
        }
    }

    // Method to handle adding a location as a favorite
    private void addToFavorites(GeoName location) {
        if (favoritesListView != null && location != null) {
            favoritesListView.getItems().add(location);
        } else {
            if (location == null) {
                System.out.println("Location is null");
            }
            if (this.favoritesListView == null) {
                System.out.println("favoritesListView is null");
            }
            System.out.println("favoritesListView null or location null");
        }
    }

    // Factory method to create FavoriteCell instances
//    private Callback<ListView<GeoName>, ListCell<GeoName>> getFavoriteCellFactory(Consumer<GeoName> addToFavoritesHandler) {
//        return listView -> new FavoriteCell(addToFavoritesHandler);
//    }

}
