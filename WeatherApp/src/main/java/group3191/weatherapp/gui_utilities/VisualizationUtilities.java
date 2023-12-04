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
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class VisualizationUtilities {

    private static final String TEXT_FONT_STYLE = "\"-fx-font-size: 24px;\"";

    public static void generateCurrentWeatherDisplay(WeatherDTO weather, GridPane grid) {
        //String pathToIcons = VisualizationUtilities.class.getResource("/weather_icons/").toString();
        String degreesMetric = " °C";
        String speedMetric = " m/s";

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

            String temperature = "" + weather.getMain().getTemp() + " °C";
            HBox row1 = generateMainviewFirstRow(first, temperature);

            grid.addRow(0, row1);

            //grid.add(row1, 0, 0);
            String feelsText = "" + weather.getMain().getFeels_like();
            Text feelsTextToAdd = getFeelsText(feelsText);
            //grid.add(feelsTextToAdd, 0, 1);
            HBox row2 = generateMainviewBottomRow(weather);
            //grid.add(row2, 0, 2);

            grid.addRow(1, feelsTextToAdd);
            grid.addRow(2, row2);

        }

    }

    public static void generateDayForecastDisplay(List<WeatherDTO> forecast, ScrollPane scrollPane) {
        HBox content = new HBox();
        for (WeatherDTO weather : forecast) {
            VBox day = generateDayWeather(weather);
            if (day != null) {
                content.getChildren().add(day);
            }
        }
        scrollPane.setContent(content);
    }
    public static void generateHourForecastDisplay(List<WeatherDTO> forecast, ScrollPane scrollPane) {
        HBox content = new HBox();
        for (WeatherDTO weather : forecast) {
            VBox hour = generateHourWeather(weather);
            if (hour != null) {
                content.getChildren().add(hour);
            }
        }
        scrollPane.setContent(content);
    }
    
    
    
    private static VBox generateHourWeather(WeatherDTO weather) {
        VBox hour = new VBox();
        hour.setSpacing(20);

        Text title = new Text("XX");
        hour.getChildren().add(title);

        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        //String idCode = weather.getId() + "";
        Weather first = weather.getWeather().get(0);
        String idCode = first.getId() + "";
        String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, first.getMain(), first.getDescription(), first.getIcon());
        //System.out.println("iconCode: "+iconCode);
        WeatherIcon iconToShow = handler.getIconById(iconCode);

        
        ImageView iconToDisplay = getImageView(iconToShow.getPath());
        if (iconToDisplay != null) {
            iconToDisplay.setFitHeight(25);
            iconToDisplay.setFitWidth(25);
            hour.getChildren().add(iconToDisplay);
        }
        Text temp = new Text("" + weather.getMain().getTemp());
        hour.getChildren().add(temp);
        
        ImageView arrow = getSmallArrow(weather.getWind().getDeg());
        hour.getChildren().add(arrow);

        Text wind = new Text("" + weather.getWind().getSpeed());
        hour.getChildren().add(wind);
        
        Text rain = new Text("RR");
        hour.getChildren().add(rain);
        
        Text something = new Text("XX");
        hour.getChildren().add(something);

        return hour;
    }

    private static VBox generateDayWeather(WeatherDTO weather) {
        VBox day = new VBox();
        day.setSpacing(20);

        Text title = new Text("day X");
        day.getChildren().add(title);

        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        //String idCode = weather.getId() + "";
        Weather first = weather.getWeather().get(0);
        String idCode = first.getId() + "";
        String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, first.getMain(), first.getDescription(), first.getIcon());
        //System.out.println("iconCode: "+iconCode);
        WeatherIcon iconToShow = handler.getIconById(iconCode);

        //WeatherIcon iconToShow = handler.getIconById(iconCode);
        ImageView iconToDisplay = getImageView(iconToShow.getPath());
        if (iconToDisplay != null) {
            day.getChildren().add(iconToDisplay);
        }
        Text min = new Text("min: " + weather.getMain().getTemp_min());
        day.getChildren().add(min);

        Text max = new Text("max: " + weather.getMain().getTemp_max());
        day.getChildren().add(max);

        return day;
    }

    private static Text getFeelsText(String feels) {
        String feelsText = "Feels like: " + feels + " °C";
        Text text = new Text(feelsText);
        //text.setStyle("-fx-font-size: 40px; -fx-min-height: 64px; -fx-min-width: 64px;");
        return text;
    }

    private static HBox generateMainviewFirstRow(Weather weather, String temperature) {
        HBox row1 = new HBox();
        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        String idCode = weather.getId() + "";
        String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, weather.getMain(), weather.getDescription(), weather.getIcon());
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        addIconToHBox(row1, iconToShow.getPath());
        Text text = new Text(temperature);
        text.setStyle("-fx-font-size: 40px; -fx-min-height: 64px; -fx-min-width: 64px;");
        row1.getChildren().add(text);
        return row1;

    }

    private static HBox generateMainviewBottomRow(WeatherDTO weather) {
        HBox row = new HBox();
        row.setSpacing(20);

        String airText = "Air Quality: ";
        airText += weather.getAirQuality().getDescription();
        //weather.
        Text airQuality = new Text(airText);
        Text rainAmmount = new Text("rain ammount -");
        airQuality.setStyle(TEXT_FONT_STYLE);
        rainAmmount.setStyle(TEXT_FONT_STYLE);
        row.getChildren().add(airQuality);
        row.getChildren().add(rainAmmount);
        addWindInfo(row, weather.getWind().getDeg(), weather.getWind().getSpeed());
        return row;

    }

    private static void addWindInfo(HBox hbox, int windDirection, double windSpeed) {
        // Create an arrow using a Polygon

        ImageView arrow = new ImageView("/icons/arrow.png");

        arrow.getTransforms().add(new Rotate(windDirection, 13, 16));
        //Polygon arrow = new Polygon();
        //arrow.getPoints().addAll(0.0, 0.0, 10.0, 20.0, -10.0, 20.0);
//        arrow.setRotate(windDirection); // Set the rotation based on wind direction

        // Create a label for displaying wind speed
        String winds = "" + windSpeed + "m/s";
        Text windText = new Text(winds);
        windText.setStyle(TEXT_FONT_STYLE);

        // Add arrow and speedLabel to the HBox
        hbox.getChildren().addAll(arrow, windText);
    }
    
    private static ImageView getSmallArrow(int windDirection)
    {
        ImageView arrow = new ImageView("/icons/arrow.png");
        arrow.getTransforms().add(new Rotate(windDirection, 13, 16));
        arrow.setFitHeight(12);
        arrow.setFitWidth(12);
        return arrow;
    }

    private static void addIconToHBox(HBox hbox, String fileName) {
        try {
            ImageView toShow = new ImageView(fileName);
            if (toShow != null) {
                toShow.setFitHeight(64);
                toShow.setFitWidth(64);
                hbox.getChildren().add(toShow);
            }

        } catch (Exception e) {
            System.out.println("error in: " + e);

        }
    }

    private static ImageView getImageView(String fileName) {
        try {
            ImageView toShow = new ImageView(fileName);
            if (toShow != null) {
                toShow.setFitHeight(64);
                toShow.setFitWidth(64);
                return toShow;
            }

        } catch (Exception e) {
            System.out.println("error in: " + e);
            return null;
        }
        return null;
    }

    private static void addIconToGrid(String fileName, GridPane grid) {
        // Create an Image from the given path
        try {
            ImageView toShow = getImageView(fileName);
            if (toShow != null) {
                grid.add(toShow, 0, 0);
            }

        } catch (Exception e) {
            System.out.println("error in: " + e);

        }

        System.out.println("pathToIcon: " + fileName);

        // Modify the column and row indices as needed
        // You might need to set some properties of the ImageView or handle exceptions based on your requirements
    }

}
