package group3191.db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import group3191.models.geo.GeoName;
import group3191.utils.Settings;


public class DatabaseHandler {

    private static File dbFile;
    private static List<GeoName> favorites = new ArrayList<GeoName>();
    private static List<GeoName> locationHistory = new ArrayList<GeoName>();
    private static GeoName lastSearch = null;
    private static String unitValue = "Metric";

    private static Gson gson = new Gson();
    
    /**
    * COnly initialized once at the start of the application
    */ 
    public static void Init() {
        initFile();
        try {
            readFromDb();
        } catch (IOException e) {
            System.out.println("FATAL ERROR: reading database file failed!");
        }
    }

    private static void initFile() {
        // Check if the file exists
        String fileName = Settings.DB_FILE_NAME;
        dbFile = new File(fileName);

        if (!dbFile.exists()) {
            try {
                Path filePath = Paths.get(fileName);
                // Create the file
                Files.createFile(filePath);

                System.out.println("Db file created: " + fileName);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        } else {
            System.out.println("Db file already exists: " + fileName);
        }
    }

    /**
     * Reads the database file and saves favorites and last search results
     * 
     * @throws IOException if reading fails
     */
    private static void readFromDb() throws IOException {
        try (Reader reader = new FileReader(dbFile)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement == null || jsonElement.isJsonNull()) {
                return;
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray favoritesArr = jsonObject.getAsJsonArray("favorites");
            // get favorites
            if (favoritesArr != null) {
                favoritesArr.forEach(fav -> {
                    JsonObject favObj = fav.getAsJsonObject();
                    GeoName geoName = gson.fromJson(favObj, GeoName.class);
                    //Search search = gson.fromJson(favObj, Search.class);
                    favorites.add(geoName);
                });
            }
            
            // get last search
            JsonObject lastSearchObj = jsonObject.getAsJsonObject("lastSearch");
            // Check if lastSearchObj is not null and not JsonNull
            if (lastSearchObj != null && lastSearchObj.get("name") != null && !lastSearchObj.isJsonNull()) {
                lastSearch = gson.fromJson(lastSearchObj, GeoName.class);
            } else {
                lastSearch = null;
            }

            // get locationHistory
            JsonArray locationHistoryArr = jsonObject.getAsJsonArray("locationHistory");
            if (locationHistoryArr != null) {
                locationHistoryArr.forEach(loc -> {
                    JsonObject locObj = loc.getAsJsonObject();
                    GeoName geoName = gson.fromJson(locObj, GeoName.class);
                    locationHistory.add(geoName);
                });
            }

            // get unit value
            JsonElement unitValueElem = jsonObject.get("unitValue");
            if (unitValueElem == null) {
                unitValue = "Metric";
            } else {
                unitValue = unitValueElem.getAsString();
            }

        } 
    }

    /**
     * Saves favorites and last search results to the database file
     * 
     * @return true if the write was successful, otherwise false
     */
     private static boolean saveToDb() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(dbFile)) {
            JsonObject jsonObject = new JsonObject();
            // Add favorites
            JsonArray favoritesArr = new JsonArray();
            favorites.forEach(fav -> {
                JsonObject favObj = gsonBuilder.toJsonTree(fav).getAsJsonObject();
                favoritesArr.add(favObj);
            });
            jsonObject.add("favorites", favoritesArr);

            // Add location history
            JsonArray locationHistoryArr = new JsonArray();
            locationHistory.forEach(loc -> {
                JsonObject locObj = gsonBuilder.toJsonTree(loc).getAsJsonObject();
                locationHistoryArr.add(locObj);
            });
            jsonObject.add("locationHistory", locationHistoryArr);

            // Add last search
            if (lastSearch != null) {
                JsonObject lastSearchObj = gsonBuilder.toJsonTree(lastSearch).getAsJsonObject();
                jsonObject.add("lastSearch", lastSearchObj);
            }

            // Add unit value
            jsonObject.addProperty("unitValue", unitValue);
            
            gsonBuilder.toJson(jsonObject, writer);
            return true;
        } catch (IOException e) {
            System.out.println("FATAL ERROR: writing database failed!");
            return false;
        }
    }

    public static GeoName getLastSearch() {
        return lastSearch;
    }

    public static void setLastSearch(GeoName lastSearch) {
        DatabaseHandler.lastSearch = lastSearch;
        saveToDb();
    }

    public static List<GeoName> getFavorites() {
        return favorites;
    }

    public static void addFavorite(GeoName search) {
        favorites.add(search);
        saveToDb();
    }

    public static List<GeoName> getLocationHistory() {
        return locationHistory;
    }

    public static void addLocationHistory(GeoName search) {
        locationHistory.add(search);
        saveToDb();
    }

    public static String getUnitValue() {
        return unitValue;
    }

    public static void setUnitValue(String unitValue) {
        DatabaseHandler.unitValue = unitValue;
        saveToDb();
    }

    public static void removeFavorite(GeoName search) {
        favorites.remove(search);
        saveToDb();
    }
}
