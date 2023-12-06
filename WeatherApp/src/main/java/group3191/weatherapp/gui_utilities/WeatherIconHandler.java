/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EeroP
 */
public class WeatherIconHandler {
    
    private List<WeatherIcon> weatherIcons;

    public WeatherIconHandler(String pathForIcons) {
        weatherIcons = new ArrayList<>();
        loadIconsFromFolder(pathForIcons);
    }
    
    private void loadIconsFromFolder(String pathForIcons) {
        
        
        File folder = new File(pathForIcons);
        File[] listOfFiles = folder.listFiles();
        //System.out.println("Absolute path: " + folder.getAbsolutePath());

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String[] parts = fileName.split("_");
                    if (parts.length >= 3 && parts[0].equals("wi")) {
                        String idDescription = parts[1];
                        String id = parts[1];
                        String description = parts[2].split("\\.")[0];
                        //String iconFileName = "/weather_icons/" + fileName;
                        //String iconFileName = fileName;
                        String iconFileName = "weather_icons\\" + fileName;
                        WeatherIcon weatherIcon = new WeatherIcon(id, description, iconFileName);
                        weatherIcons.add(weatherIcon);
                    }
                }
            }
        }
        
    }

    public WeatherIcon getIconById(String id) {
        for (WeatherIcon icon : weatherIcons) {
            if (icon.getID().equals(id)) {
                return icon;
            }
        }
        // If icon with the specified ID is not found, return a default not-found icon
        return new WeatherIcon("none", "Icon Not Found", "icons\\not_found.png");
    }

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
