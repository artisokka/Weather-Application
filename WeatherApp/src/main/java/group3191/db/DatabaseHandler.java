package group3191.db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import group3191.models.db.Search;

public class DatabaseHandler {

    private static final String fileName = "db.json";
    private static File dbFile;
    private static List<Search> favorites = new ArrayList<Search>();
    private static Search lastSearch;
    private static Gson gson = new Gson();
    
    /**
    * COnly initialized once at the start of the application
    */ 
    public static void Init() {
        dbFile = getFileFromResource(fileName);
        try {
            readFromDb();
        } catch (IOException e) {
            System.out.println("FATAL ERROR: reading database file failed!");
        }
    }

    /**
     * Reads the database file and saves favorites and last search results
     * 
     * @throws IOException if reading fails
     */
    private static void readFromDb() throws IOException {
        try (Reader reader = new FileReader(dbFile)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray favoritesArr = jsonObject.getAsJsonArray("favorites");
            // get favorites
            favoritesArr.forEach(fav -> {
                JsonObject favObj = fav.getAsJsonObject();
                Search search = gson.fromJson(favObj, Search.class);
                favorites.add(search);
            });
            // get last search
            JsonObject lastSearchObj = jsonObject.getAsJsonObject("lastSearch");
            // Check if lastSearchObj is not null and not JsonNull
            if (lastSearchObj != null && !lastSearchObj.isJsonNull()) {
                lastSearch = gson.fromJson(lastSearchObj, Search.class);
            } else {
                lastSearch = null;
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
            JsonArray favoritesArr = new JsonArray();
            favorites.forEach(fav -> {
                JsonObject favObj = gsonBuilder.toJsonTree(fav).getAsJsonObject();
                favoritesArr.add(favObj);
            });
            jsonObject.add("favorites", favoritesArr);
            if (lastSearch != null) {
                JsonObject lastSearchObj = gsonBuilder.toJsonTree(lastSearch).getAsJsonObject();
                jsonObject.add("lastSearch", lastSearchObj);
            }
            gsonBuilder.toJson(jsonObject, writer);
            return true;
        } catch (IOException e) {
            System.out.println("FATAL ERROR: writing database failed!");
            return false;
        }
    }

    public static Search getLastSearch() {
        return lastSearch;
    }

    public static void setLastSearch(Search lastSearch) {
        DatabaseHandler.lastSearch = lastSearch;
        saveToDb();
    }

    public static List<Search> getFavorites() {
        return favorites;
    }

    public static void addFavorite(Search search) {
        favorites.add(search);
        saveToDb();
    }

    public static void removeFavorite(Search search) {
        favorites.remove(search);
        saveToDb();
    }

     private static File getFileFromResource(String fileName) {

        URL resource = DatabaseHandler.class.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return new File(resource.getFile());
    }
}
