/**
 * This class handles the loading and retrieval of weather icons.
 */
package group3191.weatherapp.gui_utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WeatherIconHandler {
    
    private List<WeatherIcon> weatherIcons;

    /**
     * Constructs a WeatherIconHandler object with the specified path for icons.
     * @param pathForIcons The path where the weather icons are located.
     */
    public WeatherIconHandler(String pathForIcons) {
        weatherIcons = new ArrayList<>();
        loadIconsFromFolder(pathForIcons);
    }
    
    /**
     * Loads weather icons from the specified folder.
     * @param pathForIcons The path where the weather icons are located.
     */
    private void loadIconsFromFolder(String pathForIcons) {
        File folder = new File(pathForIcons);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String[] parts = fileName.split("_");
                    if (parts.length >= 3 && parts[0].equals("wi")) {
                        String id = parts[1];
                        String description = parts[2].split("\\.")[0];
                        String iconFileName = "weather_icons\\" + fileName;
                        WeatherIcon weatherIcon = new WeatherIcon(id, description, iconFileName);
                        weatherIcons.add(weatherIcon);
                    }
                }
            }
        }
    }

    /**
     * Retrieves a weather icon by its ID.
     * @param id The ID of the weather icon.
     * @return The WeatherIcon object with the specified ID, or a default not-found icon if not found.
     */
    public WeatherIcon getIconById(String id) {
        for (WeatherIcon icon : weatherIcons) {
            if (icon.getID().equals(id)) {
                return icon;
            }
        }
        // If icon with the specified ID is not found, return a default not-found icon
        return new WeatherIcon("none", "Icon Not Found", "icons\\not_found.png");
    }

    /**
     * Retrieves a weather icon by its description.
     * @param description The description of the weather icon.
     * @return The WeatherIcon object with the specified description, or a default not-found icon if not found.
     */
    public WeatherIcon getIconByDescription(String description) {
        for (WeatherIcon icon : weatherIcons) {
            if (icon.getDescription().equalsIgnoreCase(description)) {
                return icon;
            }
        }
        // If icon with the specified description is not found, return a default not-found icon
        return new WeatherIcon("none", "Icon Not Found", "icons\\not_found.png");
    }
    
}
