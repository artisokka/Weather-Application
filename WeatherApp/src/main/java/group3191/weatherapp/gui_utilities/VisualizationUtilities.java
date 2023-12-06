/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

/**
 *
 * @author EeroP
 */


import group3191.models.weather.DailyWeatherDTO;
import group3191.models.weather.WeatherDTO;
import group3191.models.weather.Weather;
import static group3191.weatherapp.gui_utilities.WeatherIconMapping.getIconCodeByWeather;
import java.util.List;

import javafx.scene.image.ImageView;
import java.time.LocalDateTime;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/**
 * Utility class for generating visualizations related to weather data.
 */
public class VisualizationUtilities {

    private static final String TEXT_FONT_STYLE = "-fx-font-size: 18px;";
    private static final String METRIC_DEGREES = " °C";
    private static final String METRIC_SPEED = " m/s";
    
     private static final String IMPERIAL_DEGREES = " °F";
    private static final String IMPERIAL_SPEED = " mph";

    
    /**
     * Generates the current weather display based on the provided weather data.
     * 
     * @param weather The WeatherDTO object containing the weather data.
     * @param scrollPane The ScrollPane object to display the weather content.
     * @param unitSystem The unit system to use for displaying temperature and wind speed.
     */
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
        
        List<Weather> weatherList = weather.getWeather();
        if (weatherList.size() > 0) {

            String temperature = 
                    String.format("%.1f" + degEnd, weather.getMain().getTemp());
            HBox row1 = generateMainviewFirstRow(weather, temperature);
            
            content.getChildren().add(row1);

            
            String feelsText = String.format("%.1f" + degEnd, 
                    weather.getMain().getFeels_like());
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

    

    /**
     * Generates the display for the day forecast based on the provided list of DailyWeatherDTO objects.
     * The generated display is added to the specified ScrollPane.
     *
     * @param forecast   the list of DailyWeatherDTO objects representing the forecast for multiple days
     * @param scrollPane the ScrollPane to which the generated display will be added
     */
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
    /**
     * Generates the hour forecast display based on the given forecast data.
     * 
     * @param forecast the list of weather data for the forecast
     * @param scrollPane the scroll pane to display the forecast
     * @param useOneHour flag indicating whether to display forecast for every hour or every three hours
     */
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
    
    /**
     * Returns an ImageView object representing the weather icon for the given WeatherDTO object.
     *
     * @param weather The WeatherDTO object containing weather information.
     * @return The ImageView object representing the weather icon.
     */
    private static ImageView getWeatherIconImageView(WeatherDTO weather)
    {
        String iconCode = getIconCodeByWeather(weather);
        WeatherIconHandler handler = 
                new WeatherIconHandler("src\\main\\resources\\weather_icons");
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        return getImageView(iconToShow.getPath());
    }
    
    /**
     * Generates a VBox container for displaying weather information for a specific hour.
     * 
     * @param weather   the WeatherDTO object containing weather information
     * @param hourTime  the hour time to be displayed as the title
     * @return          the generated VBox container
     */
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

    /**
     * Generates a VBox container for displaying the weather information of a specific day.
     * 
     * @param weather the DailyWeatherDTO object containing the weather information for the day
     * @param date the date of the day in string format
     * @return the VBox container with the weather information
     */
    private static VBox generateDayWeather(DailyWeatherDTO weather, String date) {
        VBox day = new VBox();
        day.setAlignment(Pos.CENTER);
        day.setSpacing(20);
        Text title = new Text(date);
        day.getChildren().add(title);
        WeatherIconHandler handler = 
                new WeatherIconHandler("src\\main\\resources\\weather_icons");

        String iconCode = getIconCodeByWeather(weather);
        
        WeatherIcon iconToShow = handler.getIconById(iconCode);
        ImageView iconToDisplay = getImageView(iconToShow.getPath());
        
        if (iconToDisplay != null) {
            day.getChildren().add(iconToDisplay);
        }
        
        String minText = String.format("min: %.0f", weather.getTemp().getMin());
        Text min = new Text(minText);
        day.getChildren().add(min);
        
        String maxText = String.format("max: %.0f", weather.getTemp().getMax());

        Text max = new Text(maxText);
        day.getChildren().add(max);

        return day;
    }

    /**
     * Creates a Text object with the specified feels value.
     *
     * @param feels the feels value to be displayed
     * @return a Text object representing the feels value
     */
    private static Text getFeelsText(String feels) {
        String feelsText = "Feels like: " + feels;
        Text text = new Text(feelsText);
        text.setStyle(TEXT_FONT_STYLE);
        return text;
    }

    /**
     * Generates the first row of the main view.
     * 
     * @param weather the WeatherDTO object containing weather information
     * @param temperature the temperature to be displayed
     * @return the HBox representing the first row of the main view
     */
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

    /**
     * Generates a row containing air quality, rain amount, and snow amount information in an HBox.
     *
     * @param weather the WeatherDTO object containing weather information
     * @return the HBox containing the generated row
     */
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

    /**
     * Generates a row containing wind information.
     * 
     * @param windDirection the direction of the wind in degrees
     * @param windSpeed the speed of the wind
     * @param speedEnd the unit of the wind speed
     * @return the generated HBox containing the wind information
     */
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
    
    /**
     * Creates an ImageView object with the specified wind direction arrow image.
     *
     * @param windDirection the direction of the wind in degrees
     * @return the ImageView object with the wind direction arrow image
     */
    private static ImageView getSmallArrow(int windDirection)
    {
        ImageView arrow = new ImageView("/icons/arrow.png");
        //arrow.getTransforms().add(new Rotate(windDirection, 13, 16));
        arrow.getTransforms().add(new Rotate(windDirection, 6,6));
        arrow.setFitHeight(12);
        arrow.setFitWidth(12);
        return arrow;
    }

   
    /**
     * Creates an ImageView object with the specified image file.
     *
     * @param fileName the name of the image file
     * @return the created ImageView object, or null if an error occurs
     */
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

   

}
