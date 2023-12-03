/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

/**
 *
 * @author EeroP
 */
public class WeatherIcon {
    private String ID;
    private String description;
    private String path;
    

    public WeatherIcon(String ID, String description, String path) {
        this.ID = ID;
        this.description = description;
        this.path = path;
    }
    


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "WeatherIcon{" + "ID=" + ID + ", description=" + description + ", path=" + path + '}';
    }
    
    
}
