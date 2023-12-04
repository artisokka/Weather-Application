/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

/**
 *
 * @author EeroP
 */
import group3191.models.geo.GeoName;
import group3191.models.weather.WeatherDTO;
import group3191.models.weather.Weather;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class VisualizationUtilities {

    public static void generateCurrentWeatherDisplay(WeatherDTO weather, GridPane grid) {
        //String pathToIcons = VisualizationUtilities.class.getResource("/weather_icons/").toString();
        grid.getChildren().clear();
        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        List<Weather> weatherList = weather.getWeather();
        if (weatherList.size() > 0) {
            Weather first = weatherList.get(0);
            String idCode = first.getId() + "";
            String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, first.getMain(), first.getDescription(), first.getIcon());
            //System.out.println("iconCode: "+iconCode);
            WeatherIcon iconToShow = handler.getIconById(iconCode);
            //addIconToGrid(iconToShow.getPath(), grid);
            
            String temperature = ""+ weather.getMain().getTemp() + " °C";
            HBox row1 = generateMainviewFirstRow(first, temperature);
            grid.add(row1, 0, 0);
            String feelsText = "" + weather.getMain().getFeels_like();
            Text feelsTextToAdd = getFeelsText(feelsText);
            grid.add(feelsTextToAdd, 0, 1);
            HBox row2 = generateMainviewBottomRow(weather);
            grid.add(row2, 0, 2);
            

        }

    }
    
    private static Text getFeelsText(String feels)
    {
        String feelsText = "Feels like: "+ feels +" °C";
        Text text = new Text(feelsText);
        //text.setStyle("-fx-font-size: 40px; -fx-min-height: 64px; -fx-min-width: 64px;");
        return text;
    }
    
    private static HBox generateMainviewFirstRow(Weather weather, String temperature)
    {
        HBox row1 = new HBox();
        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        String idCode = weather.getId() + "";
        String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, weather.getMain(), weather.getDescription(), weather.getIcon());
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        addIconToHBox(row1, iconToShow.getPath());
        Text text = new Text(temperature);
        //text.setStyle("-fx-font-size: 40px; -fx-min-height: 64px; -fx-min-width: 64px;");
        row1.getChildren().add(text);
        return row1;
        
    }
    
    private static HBox generateMainviewBottomRow(WeatherDTO weather)
    {
        HBox row = new HBox();
        String airText = "Air Quality: not implemented";
        //weather.
        Text airQuality = new Text(airText);
        Text rainAmmount = new Text("rain ammount, not imlpemented");
        row.getChildren().add(airQuality);
        row.getChildren().add(rainAmmount);
        addWindInfo(row, weather.getWind().getDeg(), weather.getWind().getSpeed());
        return row;
        
    }
    
    private static void addWindInfo(HBox hbox, int windDirection, double windSpeed) {
        // Create an arrow using a Polygon
        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(0.0, 0.0, 10.0, 20.0, -10.0, 20.0);
        arrow.setRotate(windDirection); // Set the rotation based on wind direction

        // Create a label for displaying wind speed
        String winds = ""+ windSpeed + "m/s";
        Text windText = new Text(winds);
        windText.setStyle("-fx-font-size: 24px;");
        

        // Add arrow and speedLabel to the HBox
        hbox.getChildren().addAll(arrow, windText);
    }
    
    private static void addIconToHBox(HBox hbox, String fileName)
    {
        try {
                ImageView toShow = new ImageView(fileName);
                if(toShow != null)
                {
                    toShow.setFitHeight(64);
                    toShow.setFitWidth(64);
                    hbox.getChildren().add(toShow);
                }
                

            } catch (Exception e) {
                System.out.println("error in: " +e);

            }
    }

    private static void addIconToGrid(String fileName, GridPane grid) {
        // Create an Image from the given path
        try {
                ImageView toShow = new ImageView(fileName);
                if(toShow != null)
                {
                    toShow.setFitHeight(64);
                    toShow.setFitWidth(64);
                    grid.add(toShow, 0, 0);
                }
                

            } catch (Exception e) {
                System.out.println("error in: " +e);

            }
        
        System.out.println("pathToIcon: " + fileName);

        // Modify the column and row indices as needed
        // You might need to set some properties of the ImageView or handle exceptions based on your requirements
    }

}
