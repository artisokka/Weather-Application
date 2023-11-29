/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import com.google.gson.Gson;

import group3191.models.geo.GeoName;
import group3191.models.geo.GeoNamesResponse;
import group3191.utils.Api;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import group3191.utils.ApiHandlerGeoNames;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author EeroP
 */
public class Search_guiController implements Initializable {

    private ArrayList<GeoName> favorites;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Label locationLabel;

    @FXML
    private TextField locationSearchTextField;

    @FXML
    private Label resultsLabel;

//    @FXML
//    private ListView<GeoName> resultsListView;
    @FXML
    private Tab searchTab;

    @FXML
    private Label favoritesLabel;

//    @FXML
//    private ListView<GeoName> favoritesListView;
    @FXML
    private Tab favoritesTab;

    @FXML
    private ScrollPane resultsScrollPane;

    @FXML
    private GridPane resultsGridPane;

    @FXML
    private Label favoriteGridPaneLabel;

    @FXML
    private Label locationGridPaneLabel;

    @FXML
    private Label favoriteLocationLabel;

    @FXML
    private Label favoriteRemoveLabel;

    @FXML
    private ScrollPane favoritesScrollPane;

    @FXML
    private GridPane favoritesGridPane;

    @FXML
    void searchTextFieldOnAction(ActionEvent event) {
        this.updateSearchResults();
//        this.resetGrid(resultsGridPane, "Favorite");
//        String textContent = this.locationSearchTextField.getText();
//        if (!textContent.isEmpty() && !textContent.isBlank()) {
//            GeoNamesResponse result = ApiHandlerGeoNames.getLocationSuggestions(textContent);
//            if (result != null) {
//                this.addResultToGrid(result);
//            }
//        }
    }

    void updateSearchResults() {
        this.resetGrid(resultsGridPane, "Favorite");
        String textContent = this.locationSearchTextField.getText();
        if (!textContent.isEmpty() && !textContent.isBlank()) {
            GeoNamesResponse result = ApiHandlerGeoNames.getLocationSuggestions(textContent);
            if (result != null) {
                this.addResultToGrid(result);
            }
        }
    }

    void resetGrid(GridPane grid, String seccondLabel) {
        grid.getChildren().clear();
        Label location = new Label();
        location.setText("Location");
        location.setPadding(new Insets(0, 15, 0, 15));
        Label secondary = new Label();
        secondary.setText(seccondLabel);
        secondary.setPadding(new Insets(0, 15, 0, 15));

        grid.addRow(0, location, secondary);
    }

    void addResultToGrid(GeoNamesResponse results) {
        if (results.getSize() > 0) {
            for (int row = 0; row < results.getSize(); row++) {
                //Label location = new Label();
                GeoName currentLocation = results.getIndex(row);

                Button location = new Button();
                location.setText(currentLocation.toString());
                Button favoriteButton = new Button();
                if (this.isFavorite(currentLocation)) {
                    //favoriteButton.setText("old_favorite");
                    favoriteButton.setGraphic(new ImageView("/icons/fav_icon_full.png"));
                } else {
                    //favoriteButton.setText("not");
                    favoriteButton.setGraphic(new ImageView("/icons/fav_icon_empty.png"));
                }
                this.resultsGridPane.setFillWidth(location, true);
                location.setOnAction(
                        //event -> this.addFavorite(currentLocation, favoriteButton));
                        event -> this.setWeatherLocation(currentLocation));

                favoriteButton.setOnAction(event
                        -> this.toggleFavorite(currentLocation, favoriteButton));

                this.resultsGridPane.addRow(row + 1, location, favoriteButton);
            }
            this.adjustButtonWidths(resultsGridPane);
            //ColumnConstraints columnConstraints = new ColumnConstraints();

        }
    }

    void adjustButtonWidths(GridPane gridPane) {

        // Loop through all buttons in the first column to find the maximum width
        Platform.runLater(()
                -> {
            double maxWidth = 0.0;
            for (int i = 1; i < gridPane.getRowCount(); i++) {
                Button button = (Button) gridPane.getChildren().get(i * gridPane.getColumnCount());
                double buttonWidth = button.getWidth(); // Get preferred width of the button
                int wtf = 1;

                if (buttonWidth > maxWidth) {
                    maxWidth = buttonWidth;
                }
            }

            // Set the maximum width to all buttons in the first column
            for (int i = 1; i < gridPane.getRowCount(); i++) {
                Button button = (Button) gridPane.getChildren().get(i * gridPane.getColumnCount());
                button.setPrefWidth(maxWidth);
            }
        });
    }

    void updateFavorites() {
        this.resetGrid(this.favoritesGridPane, "Remove");

        if (this.favorites.size() > 0) {
            for (int row = 0; row < this.favorites.size(); row++) {
                //Label location = new Label();
                GeoName currentLocation = favorites.get(row);
                Button location = new Button();
                location.setText(currentLocation.toString());
                Button removeButton = new Button();

                location.setOnAction(
                        //event -> this.addFavorite(currentLocation, favoriteButton));
                        event -> this.setWeatherLocation(currentLocation));

                removeButton.setOnAction(event
                        -> this.removeFavorite(currentLocation));

                this.favoritesGridPane.addRow(row + 1, location, removeButton);
            }
            this.adjustButtonWidths(this.favoritesGridPane);
        }

    }

    void removeFavorite(GeoName location) {
        this.favorites.remove(location);
        this.updateFavorites();
        this.updateSearchResults();
    }

    public boolean isFavorite(GeoName location) {
        return this.favorites.contains(location);
        //var children = this.favoritesGridPane.getchi;

        //boolean containsLocation = this.favoritesListView.getItems().contains(location);
        //return this.favoritesListView.getItems().contains(location);
    }

    public void toggleFavorite(GeoName location, Button favoriteButton) {
        if (isFavorite(location)) {
            this.favorites.remove(location);
            //this.favoritesListView.getItems().remove(location);
            //favoriteButton.setText("");
            favoriteButton.setGraphic(new ImageView("/icons/fav_icon_empty.png"));

        } else {
            //favoriteButton.setText("isFavorite");
            favoriteButton.setGraphic(new ImageView("/icons/fav_icon_full.png"));
            this.favorites.add(location);
//            var items = this.favoritesListView.getItems();
//            items.add(location);
        }
        this.updateFavorites();

    }

    void setWeatherLocation(GeoName location) {
        if (this.mainController != null) {
            Weatherapp_guiController weatherController = mainController.getWeatherController();
            if (weatherController != null) {

                if (location != null) {
                    weatherController.setWeather(location);
                    weatherController.showWeather();
                    //setWeather(selected);
                }
            }
        }
    }

    @FXML
    void favoritesClicked(MouseEvent event) {

    }

//    @FXML
//    void resultsClicked(MouseEvent event) {
//        if (this.mainController != null) {
//            Weatherapp_guiController weatherController = mainController.getWeatherController();
//            if (weatherController != null) {
//                GeoName selected = this.resultsListView.getSelectionModel().getSelectedItem();
//                if (selected != null) {
//                    weatherController.setWeather(selected);
//                    weatherController.showWeather();
//                    //setWeather(selected);
//                }
//            }
//        }
//
//    }
    private void testLocationApi(String location) {

        Api api = new Api();
        Gson gson = new Gson();
        String responseBody = api.lookUpLocation(location);
        System.out.println(responseBody);

        //WeatherDTO weatherDTO = gson.fromJson(responseBody, WeatherDTO.class);
        //return weatherDTO;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.favorites = new ArrayList();
//        resultsListView.setCellFactory(getFavoriteCellFactory(this::addToFavoritesHandler, this::removeFromFavoritesHandler));
//        favoritesListView.setCellFactory(getFavoriteCellFactory(this::addToFavoritesHandler, this::removeFromFavoritesHandler));

    }

//    private Callback<ListView<GeoName>, ListCell<GeoName>> getSearchResultsCellFactory() {
//        return listView -> new ListCell<GeoName>() {
//            @Override
//            protected void updateItem(GeoName item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                    String textToShow = String.format("%s, %s, lat=%s, long=%s",
//                            item.getCityName(), item.getCountryName(),
//                            item.getLat(), item.getLng());
//                    setText(textToShow);
//                    // setText(item.getName()); // Display the GeoName name in the search results
//                    // You can add more customization for the search results cell if needed
//                }
//            }
//        };
//    }
//
//    public void addToFavoritesHandler(GeoName location) {
//        if (favoritesListView != null && location != null) {
//            favoritesListView.getItems().add(location);
//        }
//    }
//
//    public void removeFromFavoritesHandler(GeoName location) {
//        if (favoritesListView != null && location != null) {
//            favoritesListView.getItems().remove(location);
//        }
//    }
//
//    private Callback<ListView<GeoName>, ListCell<GeoName>> getFavoriteCellFactory(Consumer<GeoName> addToFavoritesHandler, Consumer<GeoName> removeFromFavoritesHandler) {
//        return listView -> new FavoriteCell(addToFavoritesHandler, removeFromFavoritesHandler);
//    }
//
//    public void addToFavorites(GeoName location) {
//        if (favoritesListView != null && location != null) {
//            favoritesListView.getItems().add(location);
//        }
//    }
}
