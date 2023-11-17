/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.weatherapp.utils.formatters;
/**
 *
 * @author dmmile
 */
public interface URIFormatter {
    public void AddParameter(String key, String value);
    //public void AddKey(String key);
    public String GetURI();
}
