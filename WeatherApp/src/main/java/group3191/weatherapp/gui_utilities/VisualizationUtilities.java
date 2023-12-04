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

import com.kitfox.svg.*;
import com.kitfox.svg.app.beans.SVGIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class VisualizationUtilities {

    public static void generateCurrentWeatherDisplay(WeatherDTO weather, GridPane grid) {
        //WeatherIconHandler handler = new WeatherIconHandler("C:\\Users\\EeroP\\Documents\\Opiskelu_tuni\\COMP_CS_140_Ohjelmointi_3\\ryhmatyo\\group3191\\WeatherApp\\src\\main\\resources\\weather_icons");
        WeatherIconHandler handler = new WeatherIconHandler("src\\main\\resources\\weather_icons");
        List<Weather> weatherList = weather.getWeather();
        if (weatherList.size() > 0) {
            Weather first = weatherList.get(0);
            String idCode = first.getId() + "";
            String iconCode = WeatherIconMapping.getLocalWeatherIconCode(idCode, first.getMain(), first.getDescription(), first.getIcon());
            //System.out.println("iconCode: "+iconCode);
            WeatherIcon iconToShow = handler.getIconById(iconCode);
            addIconToGrid(iconToShow.getPath(), grid);

        }

    }

    private static void addIconToGrid(String fileName, GridPane grid) {
        // Create an Image from the given path
        try {
                InputStream svgStream = VisualizationUtilities.class.getResourceAsStream("/weather_icons/" + fileName);
                
                java.awt.image.BufferedImage bufferedImage = ImageIO.read(svgStream);
                File tempFile = File.createTempFile("temp", ".png");
                ImageIO.write(bufferedImage, "png", tempFile);

                Image image = new Image(new FileInputStream(tempFile));

                ImageView imageView = new ImageView(image);

                grid.add(imageView, 0, 0); // Add to column 0, row 0

            } catch (Exception e) {
                System.out.println("error in: " +e);

            }
        
        System.out.println("pathToIcon: " + fileName);

        // Modify the column and row indices as needed
        // You might need to set some properties of the ImageView or handle exceptions based on your requirements
    }

}
