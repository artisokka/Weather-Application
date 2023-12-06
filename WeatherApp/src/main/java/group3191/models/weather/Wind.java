package group3191.models.weather;

/**
 * This class is used to represent the wind.
 */
public class Wind {
    private double speed;
    private int deg;
    private double gust;


    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return this.deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getGust() {
        return this.gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    @Override
    public String toString() {
        return "Wind{" + "speed=" + speed + ", deg=" + deg + ", gust=" + gust + '}';
    }
    
    
}
