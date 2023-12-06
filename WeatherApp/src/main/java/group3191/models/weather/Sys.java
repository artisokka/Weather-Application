package group3191.models.weather;

/**
 * This class is used to represent the basic details of the weather.
 */
public class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;


    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return this.sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
    
}
