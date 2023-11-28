module group3191.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;
    requires io.github.cdimascio.dotenv.java;
    requires java.net.http;

    opens group3191.weatherapp to javafx.fxml;
    opens group3191.models;
    opens group3191.models.db to com.google.gson;
    opens group3191.models.weather to com.google.gson;
    opens group3191.models.geo to com.google.gson;
    exports group3191.weatherapp;
}
