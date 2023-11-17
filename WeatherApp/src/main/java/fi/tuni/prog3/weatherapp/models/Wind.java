package fi.tuni.prog3.weatherapp.models;

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
    
}
