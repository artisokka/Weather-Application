/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package fi.tuni.prog3.weatherapp.utils.formatters;

import java.util.TreeMap;

/**
 *
 * @author dmmile
 */
abstract class Formatter implements URIFormatter {
    protected String URI;
    protected String key;
    protected String endpoint;
    protected TreeMap<String, String> keyValues;
    
    public Formatter(String URI, String endpoint, String key) {
        this.URI = URI;
        this.key = key;
        this.endpoint = endpoint;
        keyValues = new TreeMap<>();
    }
    
    @Override
    public abstract void AddParameter(String key, String value);

    //@Override
    //public abstract void AddKey(String key);

    @Override
    public abstract String GetURI();
}
