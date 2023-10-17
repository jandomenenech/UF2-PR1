package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Funcions csvReader = new Funcions();
        List<Map<String, String>> data = csvReader.readCSV("src/Games.txt");
        Funcions.exportToCSV(data, "src/ExportedGames.csv");
        }
}


