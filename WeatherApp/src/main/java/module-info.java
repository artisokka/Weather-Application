module fi.tuni.progthree.weatherapp {
    requires javafx.controls;
    requires com.google.gson;
    requires java.net.http;
    requires io.github.cdimascio.dotenv.java;
    opens fi.tuni.prog3.weatherapp.models;
    exports fi.tuni.prog3.weatherapp;
}
