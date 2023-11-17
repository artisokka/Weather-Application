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
public interface HeaderFormatter 
{
    public TreeMap<String,String> GetHeaders();

    public void AddHeader(String key, String value);
}
