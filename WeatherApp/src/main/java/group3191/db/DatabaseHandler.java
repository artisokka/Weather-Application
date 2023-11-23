package group3191.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
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

     private static boolean saveToDb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeToFile'");
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
