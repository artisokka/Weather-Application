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
public class DefaultHeaderFormatter implements HeaderFormatter {
    public DefaultHeaderFormatter() {
    
    }

    @Override
    public TreeMap<String, String> GetHeaders() {
        return new TreeMap<>();
    }

    @Override
    public void AddHeader(String key, String value) {
        return;
    }
}
