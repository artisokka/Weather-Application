package group3191.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DatabaseHandler {

    private static final String fileName = "db.json";
    private static File dbFile;

    
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
            System.out.println(jsonObject.get("test"));
        } 
    }

     private static boolean saveToDb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeToFile'");
    }

     private static File getFileFromResource(String fileName) {

        URL resource = DatabaseHandler.class.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        }
        return new File(resource.getFile());
    }
}
