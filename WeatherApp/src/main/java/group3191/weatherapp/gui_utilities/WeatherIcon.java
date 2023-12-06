/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group3191.weatherapp.gui_utilities;

/**
 *
 * @author EeroP
 */
/**
 * Represents a weather icon with its ID, description, and path.
 */
public class WeatherIcon {
    private String ID;
    private String description;
    private String path;

    /**
     * Constructs a WeatherIcon object with the specified ID, description, and path.
     *
     * @param ID          the ID of the weather icon
     * @param description the description of the weather icon
     * @param path        the path to the weather icon image
     */
    public WeatherIcon(String ID, String description, String path) {
        this.ID = ID;
        this.description = description;
        this.path = path;
    }

    /**
     * Returns the ID of the weather icon.
     *
     * @return the ID of the weather icon
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets the ID of the weather icon.
     *
     * @param ID the ID of the weather icon
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Returns the description of the weather icon.
     *
     * @return the description of the weather icon
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the weather icon.
     *
     * @param description the description of the weather icon
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the path to the weather icon image.
     *
     * @return the path to the weather icon image
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path to the weather icon image.
     *
     * @param path the path to the weather icon image
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns a string representation of the WeatherIcon object.
     *
     * @return a string representation of the WeatherIcon object
     */
    @Override
    public String toString() {
        return "WeatherIcon{" +
                "ID='" + ID + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
