package group3191.models.weather;

/**
 * This class is used to represent the weather.
 */
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return this.main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" + "id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon + '}';
    }
    
}
