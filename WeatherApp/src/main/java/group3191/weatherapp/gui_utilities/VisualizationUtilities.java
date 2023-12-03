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

    private static void addIconToGrid(String pathToIcon, GridPane grid) {
        // Create an Image from the given path
        if (!pathToIcon.equals("notfound.png")) {
            SVGPath svgPath = new SVGPath();
        svgPath.setContent(pathToIcon);
        grid.add(svgPath, 0, 0);
        } else {
            //Image icon = new Image("/weather_icons/wi_34n_mostly-cloudy-low-snow");
            Image icon = new Image("/weather_icons/wi_34n_mostly-cloudy-low-snow.svg");
            

            // Create an ImageView to display the icon
            ImageView imageView = new ImageView(icon);

            // Add the ImageView to the GridPane
            grid.add(imageView, /* column index */ 0, /* row index */ 0);

        }
        System.out.println("pathToIcon: " + pathToIcon);

        // Modify the column and row indices as needed
        // You might need to set some properties of the ImageView or handle exceptions based on your requirements
    }

}
