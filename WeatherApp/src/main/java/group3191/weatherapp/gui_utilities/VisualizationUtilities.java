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
import group3191.models.weather.DailyWeatherDTO;
import group3191.models.weather.WeatherDTO;
import group3191.models.weather.Weather;
import static group3191.weatherapp.gui_utilities.WeatherIconMapping.getIconCodeByWeather;
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
import java.time.LocalDateTime;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class VisualizationUtilities {

    private static final String TEXT_FONT_STYLE = "-fx-font-size: 18px;";
    private static final String METRIC_DEGREES = " °C";
    private static final String METRIC_SPEED = " m/s";
    
     private static final String IMPERIAL_DEGREES = " °F";
    private static final String IMPERIAL_SPEED = " mph";

    
    public static void generateCurrentWeatherDisplay(WeatherDTO weather, 
            ScrollPane scrollPane, String unitSystem) {
        
        String degEnd = METRIC_DEGREES;
        String speedEnd = METRIC_SPEED;
        if(unitSystem.equals("Imperial"))
        {
            degEnd = IMPERIAL_DEGREES;
            speedEnd = IMPERIAL_SPEED;
        }

        
        VBox content = new VBox();
        content.setStyle("-fx-background-color: WHITE;");
        
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);
        
        WeatherIconHandler handler =
                new WeatherIconHandler("src\\main\\resources\\weather_icons");
        List<Weather> weatherList = weather.getWeather();
        if (weatherList.size() > 0) {
            Weather first = weatherList.get(0);
            String iconCode = getIconCodeByWeather(weather);
            WeatherIcon iconToShow = handler.getIconById(iconCode);

            String temperature = "" + weather.getMain().getTemp() + degEnd;
            HBox row1 = generateMainviewFirstRow(weather, temperature);
            
            content.getChildren().add(row1);

            
            String feelsText = "" + weather.getMain().getFeels_like() + degEnd;
            Text feelsTextToAdd = getFeelsText(feelsText);
            
            content.getChildren().add(feelsTextToAdd);
            //grid.add(feelsTextToAdd, 0, 1);
            HBox row2 = generateMainviewAirRow(weather);
            
            content.getChildren().add(row2);
            
            //HBox hbox, int windDirection, double windSpeed
            HBox row3 = generateWindInfoRow(weather.getWind().getDeg(), 
                    weather.getWind().getSpeed(), speedEnd);
            
            content.getChildren().add(row3);

        }
        scrollPane.setContent(content);

    }

    

    public static void generateDayForecastDisplay(
            List<DailyWeatherDTO> forecast, ScrollPane scrollPane) {
        HBox content = new HBox();
        content.setStyle("-fx-background-color: WHITE;");
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);
        LocalDateTime now = LocalDateTime.now();  
        for (DailyWeatherDTO weather : forecast) {
            now = now.plusDays(1);
            String date = String.format("%s.%s", now.getDayOfMonth(), now.getMonthValue());
            VBox day = generateDayWeather(weather, date);
            if (day != null) {
                content.getChildren().add(day);
            }
        }
        scrollPane.setContent(content);
    }
    public static void generateHourForecastDisplay(List<WeatherDTO> forecast, 
            ScrollPane scrollPane, boolean useOneHour) {
        HBox content = new HBox();
        content.setStyle("-fx-background-color: WHITE;");
        content.setAlignment(Pos.CENTER);
        content.setSpacing(5);
        LocalDateTime now = LocalDateTime.now();
        now = now.plusHours(1);
        for (WeatherDTO weather : forecast) {
            String hourTime = String.format("%s", now.getHour());
            VBox hour = generateHourWeather(weather, hourTime);
            if (hour != null) {
                content.getChildren().add(hour);
            }
            if(useOneHour)
            {
                now = now.plusHours(1);
            } else
            {
                now = now.plusHours(3);
            }
        }
        scrollPane.setContent(content);
    }
    
    private static ImageView getWeatherIconImageView(WeatherDTO weather)
    {
        String iconCode = getIconCodeByWeather(weather);
        WeatherIconHandler handler = 
                new WeatherIconHandler("src\\main\\resources\\weather_icons");
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        return getImageView(iconToShow.getPath());
    }
    
    private static VBox generateHourWeather(WeatherDTO weather, String hourTime) {
        VBox hour = new VBox();
        hour.setSpacing(10);
        hour.setAlignment(Pos.CENTER);

        Text title = new Text(hourTime);
        hour.getChildren().add(title);
        
        ImageView iconToDisplay = getWeatherIconImageView(weather);
        if (iconToDisplay != null) {
            iconToDisplay.setFitHeight(25);
            iconToDisplay.setFitWidth(25);
            hour.getChildren().add(iconToDisplay);
        }
        String tempText = String.format("%.0f", weather.getMain().getTemp());
        Text temp = new Text(tempText);
        hour.getChildren().add(temp);
        
        ImageView arrow = getSmallArrow(weather.getWind().getDeg());
        hour.getChildren().add(arrow);

        String windText = String.format("%.0f", weather.getWind().getSpeed());
        Text wind = new Text(windText);
        hour.getChildren().add(wind);
        
        String rainText = String.format("%.1f", weather.getRainAmount());
        Text rain = new Text(rainText);
        hour.getChildren().add(rain);
        
        String snowText = String.format("%.1f", weather.getSnowAmount());
        Text something = new Text(snowText);
        hour.getChildren().add(something);

        return hour;
    }

    private static VBox generateDayWeather(DailyWeatherDTO weather, String date) {
        VBox day = new VBox();
        day.setAlignment(Pos.CENTER);
        day.setSpacing(20);
        Text title = new Text(date);
        day.getChildren().add(title);
        WeatherIconHandler handler = 
                new WeatherIconHandler("src\\main\\resources\\weather_icons");
    
        Weather first = weather.getWeather().get(0);
        String idCode = first.getId() + "";
        String iconCode = getIconCodeByWeather(weather);
        
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        ImageView iconToDisplay = getImageView(iconToShow.getPath());
        
        if (iconToDisplay != null) {
            day.getChildren().add(iconToDisplay);
        }
        
        String minText = String.format("min: %.0f", weather.getTemp().getMin());
        Text min = new Text(minText);
        day.getChildren().add(min);
        
        String maxText = String.format("max: %.0f", weather.getTemp().getMin());

        Text max = new Text(maxText);
        day.getChildren().add(max);

        return day;
    }

    private static Text getFeelsText(String feels) {
        String feelsText = "Feels like: " + feels;
        Text text = new Text(feelsText);
        text.setStyle(TEXT_FONT_STYLE);
        return text;
    }

    private static HBox generateMainviewFirstRow(WeatherDTO weather, 
            String temperature) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        ImageView iconToDisplay = getWeatherIconImageView(weather);
        row.getChildren().add(iconToDisplay);
        Text text = new Text(temperature);
        text.setStyle("-fx-font-size: 40px; -fx-min-height: 64px; -fx-min-width: 64px;");
        row.getChildren().add(text);
        return row;

    }

    private static HBox generateMainviewAirRow(WeatherDTO weather) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setSpacing(20);

        String airText = "Air Quality: ";
        airText += weather.getAirQuality().getDescription();
        //weather.
        Text airQuality = new Text(airText);
        Text rainAmmount = new Text("Rain: " + weather.getRainAmount());
        Text snowAmmount = new Text("Snow: " + weather.getSnowAmount());
        airQuality.setStyle(TEXT_FONT_STYLE);
        rainAmmount.setStyle(TEXT_FONT_STYLE);
        snowAmmount.setStyle(TEXT_FONT_STYLE);
        row.getChildren().add(airQuality);
        row.getChildren().add(rainAmmount);
        row.getChildren().add(snowAmmount);
        //addWindInfo(row, weather.getWind().getDeg(), weather.getWind().getSpeed());
        return row;

    }

    private static HBox generateWindInfoRow(int windDirection, double windSpeed,
            String speedEnd) {
        // Create an arrow using a Polygon
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER);
        row.setSpacing(20);

        ImageView arrow = new ImageView("/icons/arrow.png");

        arrow.getTransforms().add(new Rotate(windDirection, 13, 16));
        String winds = "" + windSpeed + speedEnd;
        Text windText = new Text(winds);
        windText.setStyle("-fx-font-size: 24px;");
        row.getChildren().addAll(arrow, windText);
        return row;
    }
    
    private static ImageView getSmallArrow(int windDirection)
    {
        ImageView arrow = new ImageView("/icons/arrow.png");
        //arrow.getTransforms().add(new Rotate(windDirection, 13, 16));
        arrow.getTransforms().add(new Rotate(windDirection, 6,6));
        arrow.setFitHeight(12);
        arrow.setFitWidth(12);
        return arrow;
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
            System.out.println("täss on virhe jos: "+fileName);
            return null;
        }
        return null;
    }

   

}
