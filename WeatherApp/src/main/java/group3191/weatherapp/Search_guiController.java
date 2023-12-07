/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package group3191.weatherapp;

import group3191.models.geo.GeoName;
import group3191.models.geo.GeoNamesResponse;
import group3191.utils.ApiHandlerGeoNames;
import group3191.db.DatabaseHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class for the Search GUI. Handles user interactions and
 * displays search results, favorites, and location history.
 */
public class Search_guiController implements Initializable {

    private List<GeoName> favorites;

    private MainController mainController;


    /**
     * Sets the main controller.
     *
     * @param mainController The main controller.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Label locationLabel;

    @FXML
    private TextField locationSearchTextField;

    @FXML
    private Label resultsLabel;

    @FXML
    private Tab searchTab;

    @FXML
    private Label favoritesLabel;

    @FXML
    private Label historyLabel;

    @FXML
    private Tab favoritesTab;

    @FXML
    private Tab historyTab;

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
    private Label historyLocationLabel;

    @FXML
    private Label favoriteRemoveLabel;

    @FXML
    private ScrollPane favoritesScrollPane;

    @FXML
    private GridPane favoritesGridPane;

    @FXML
    private ScrollPane historyScrollPane;

    @FXML
    private GridPane historyGridPane;

    /**
     * Handles the action when the search text field is activated. Updates the
     * search results based on the entered text.
     *
     * @param event The action event.
     */
    @FXML
    void searchTextFieldOnAction(ActionEvent event) {
        this.updateSearchResults();
    }

    /**
     * Updates the search results based on the entered text.
     */
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

    /**
     * Resets the grid with the specified secondary label.
     *
     * @param grid The grid pane.
     * @param secondLabel The secondary label.
     */
    void resetGrid(GridPane grid, String secondLabel) {
        grid.getChildren().clear();
        Label location = new Label();
        location.setText("Location");
        location.setPadding(new Insets(0, 15, 0, 15));
        Label secondary = new Label();
        secondary.setText(secondLabel);
        secondary.setPadding(new Insets(0, 15, 0, 15));

        grid.addRow(0, location, secondary);
    }

    /**
     * Gets an icon to represent a favorite
     * @param isFavorite boolean if location was in favorites
     * @return ImageView
     */
    private ImageView getFavoriteIcon(boolean isFavorite) {
        int size = 18;
        if (isFavorite) {
            ImageView icon = new ImageView("/icons/fav_icon_full.png");
            icon.setFitHeight(size);
            icon.setFitHeight(size);
            return icon;
        } else {
            ImageView icon = new ImageView("/icons/fav_icon_empty.png");
            icon.setFitHeight(size);
            icon.setFitHeight(size);
            return icon;
        }
    }

    /**
     * Checks if GeoName doesn't contain null values in Strings
     * @param location GeoName
     * @return boolean, true if valid else false
     */
    boolean isGeoNameLocationValid(GeoName location) {
        if (location == null) {
            return false;
        }
        if (location.getCityName() == null) {
            return false;
        }
        if (location.getCountryName() == null) {
            return false;
        }

        return true;
    }

    /**
     * Adds the search results to the grid.
     *
     * @param results The search results.
     */
    void addResultToGrid(GeoNamesResponse results) {
        if (results.getSize() > 0) {
            for (int row = 0; row < results.getSize(); row++) {
                GeoName currentLocation = results.getIndex(row);
                if (isGeoNameLocationValid(currentLocation)) {

                    Button location = new Button();
                    location.setText(currentLocation.toString());
                    Button favoriteButton = new Button();
                    if (this.isFavorite(currentLocation)) {

                        favoriteButton.setGraphic(getFavoriteIcon(true));
                    } else {
                        //favoriteButton.setGraphic(new ImageView("/icons/fav_icon_empty.png"));
                        favoriteButton.setGraphic(getFavoriteIcon(false));
                    }

                    this.resultsGridPane.setFillWidth(location, true);
                    location.setOnAction(event -> this.setWeatherLocation(currentLocation));

                    favoriteButton.setOnAction(event -> this.toggleFavorite(currentLocation, favoriteButton));

                    this.resultsGridPane.addRow(row + 1, location, favoriteButton);
                }

            }
            this.adjustButtonWidths(resultsGridPane);
        }
    }

    /**
     * Adjusts the widths of the buttons in the grid pane.
     *
     * @param gridPane The grid pane.
     */
    void adjustButtonWidths(GridPane gridPane) {
        Platform.runLater(() -> {
            if (gridPane.getColumnCount() > 1) {

                if (gridPane.getRowCount() > 1) {
                    double maxWidth = 0.0;
                    for (int i = 1; i < gridPane.getRowCount(); i++) {
                        if (i * gridPane.getColumnCount() < gridPane.getChildren().size()) {
                            Button button = (Button) gridPane.getChildren().get(i * gridPane.getColumnCount());
                            if (button != null) {
                                double buttonWidth = button.getWidth();

                                if (buttonWidth > maxWidth) {
                                    maxWidth = buttonWidth;
                                }
                            }
                        }
                    }

                    for (int i = 1; i < gridPane.getRowCount(); i++) {
                        if (i * gridPane.getColumnCount() < gridPane.getChildren().size()) {
                            if (maxWidth > 50) {
                                Button button = (Button) gridPane.getChildren().get(i * gridPane.getColumnCount());
                                button.setPrefWidth(maxWidth);
                            }
                        }
                    }
                }
            } else {
                double maxWidth = 0.0;
                for (int i = 1; i < gridPane.getRowCount(); i++) {
                    if (i < gridPane.getChildren().size()) {
                        Button button = (Button) gridPane.getChildren().get(i);
                        if (button != null) {
                            double buttonWidth = button.getWidth();

                            if (buttonWidth > maxWidth) {
                                maxWidth = buttonWidth;
                            }
                        }
                    }
                }

                for (int i = 1; i < gridPane.getRowCount(); i++) {
                    if (i < gridPane.getChildren().size()) {
                        if (maxWidth > 50) {
                            Button button = (Button) gridPane.getChildren().get(i);
                            button.setPrefWidth(maxWidth);
                        }
                    }
                }
            }
        });
    }

    /**
     * Updates the favorites grid.
     */
    void updateFavorites() {
        this.resetGrid(this.favoritesGridPane, "Remove");

        if (this.favorites.size() > 0) {
            for (int row = 0; row < this.favorites.size(); row++) {
                GeoName currentLocation = favorites.get(row);
                Button location = new Button();
                location.setText(currentLocation.toString());
                Button removeButton = new Button();
                removeButton.setText("Remove");

                location.setOnAction(event -> this.setWeatherLocation(currentLocation));

                removeButton.setOnAction(event -> this.removeFavorite(currentLocation));

                this.favoritesGridPane.addRow(row + 1, location, removeButton);
            }
            this.adjustButtonWidths(this.favoritesGridPane);
        }

    }

    /**
     * Updates the location history grid.
     */
    void updateHistory() {
        this.resetGrid(this.historyGridPane, "");
        List<GeoName> locationHistory = DatabaseHandler.getLocationHistory();
        if (locationHistory != null) {
            for (int row = 0; row < locationHistory.size(); row++) {
                GeoName currentLocation = locationHistory.get(row);
                Button location = new Button();
                location.setText(currentLocation.toString());

                location.setOnAction(event -> this.setWeatherLocation(currentLocation));

                this.historyGridPane.addRow(row + 1, location);
            }
            this.adjustButtonWidths(this.historyGridPane);
        }
    }

    /**
     * Removes a favorite location.
     *
     * @param location The location to remove.
     */
    void removeFavorite(GeoName location) {
        this.favorites.remove(location);
        DatabaseHandler.removeFavorite(location);
        this.updateFavorites();
        this.updateSearchResults();
    }

    /**
     * Checks if a location is a favorite.
     *
     * @param location The location to check.
     * @return true if the location is a favorite, false otherwise.
     */
    public boolean isFavorite(GeoName location) {
        return this.favorites.contains(location);
    }

    /**
     * Toggles the favorite status of a location.
     *
     * @param location The location to toggle.
     * @param favoriteButton The favorite button associated with the location.
     */
    public void toggleFavorite(GeoName location, Button favoriteButton) {
        if (isFavorite(location)) {
            this.favorites.remove(location);
            favoriteButton.setGraphic(getFavoriteIcon(false));
            //favoriteButton.setGraphic(new ImageView("/icons/fav_icon_empty.png"));
        } else {
            //favoriteButton.setGraphic(new ImageView("/icons/fav_icon_full.png"));
            favoriteButton.setGraphic(getFavoriteIcon(true));
            this.favorites.add(location);
            DatabaseHandler.addFavorite(location);
        }
        this.updateFavorites();
    }

    /**
     * Sets the weather location and updates the weather display.
     *
     * @param location The location to set.
     */
    void setWeatherLocation(GeoName location) {
        if (this.mainController != null) {
            Weatherapp_guiController weatherController = mainController.getWeatherController();
            if (weatherController != null) {
                if (location != null) {
                    weatherController.setWeather(location);
                    weatherController.showWeather();
                    DatabaseHandler.setLastSearch(location);
                    DatabaseHandler.addLocationHistory(location);
                    this.updateHistory();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.favorites = new ArrayList<>(DatabaseHandler.getFavorites());
        this.updateFavorites();
        this.updateHistory();
    }
}
