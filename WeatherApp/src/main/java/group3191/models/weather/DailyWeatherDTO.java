package group3191.models.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class is used to represent the daily weather.
 */
public class DailyWeatherDTO {

    private long dt;
    private long sunrise;
    private long sunset;

    private Temperature temp;

    @SerializedName("feels_like")
    private FeelsLike feelsLike;

    private int pressure;
    private int humidity;

    private List<Weather> weather;

    private double speed;
    private int deg;
    private double gust;
    private int clouds;
    private double pop;

    private double rain;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(FeelsLike feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    /**
     * This class is used to represent the daily weather temperature.
     */
    public static class Temperature {
        private double day;
        private double min;
        private double max;
        private double night;
        private double eve;
        private double morn;

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }

        public double getEve() {
            return eve;
        }

        public void setEve(double eve) {
            this.eve = eve;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMorn() {
            return morn;
        }

        public void setMorn(double morn) {
            this.morn = morn;
        }

        public double getNight() {
            return night;
        }

        public void setNight(double night) {
            this.night = night;
        }
    }

    /**
     * This class is used to represent the daily weather feels like temperature.
     */
    public static class FeelsLike {
        private double day;
        private double night;
        private double eve;
        private double morn;

        public double getDay() {
            return day;
        }

        public void setDay(double day) {
            this.day = day;
        }

        public double getEve() {
            return eve;
        }

        public void setEve(double eve) {
            this.eve = eve;
        }

        public double getMorn() {
            return morn;
        }

        public void setMorn(double morn) {
            this.morn = morn;
        }

        public double getNight() {
            return night;
        }

        public void setNight(double night) {
            this.night = night;
        }
    }
}
