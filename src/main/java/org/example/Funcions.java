package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Funcions {
    public List<Map<String, String>> readCSV(String filePath) {
        List<Map<String, String>> rows = new ArrayList<>();
        try {
            Reader in = new FileReader(filePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                Map<String, String> row = record.toMap();

                // Lista de claves a procesar
                String[] keysToProcess = {"Rating", "Times Listed", "Number of Reviews", "Plays", "Playing", "Backlogs", "Wishlist"};

                for (String key : keysToProcess) {
                    String value = row.get(key);
                    if (value != null && value.endsWith("K")) {
                        try {
                            double numericValue = Double.parseDouble(value.replace("K", "").trim());
                            String stringValue = String.valueOf((int)(numericValue * 1000));
                            row.put(key, stringValue);
                        } catch (NumberFormatException e) {
                            // Manejar errores de conversión
                            System.err.println("Error al convertir valor en clave " + key);
                        }
                    }
                }

                // Procesar la clave "Release Date"
                String releaseDate = row.get("Release Date");
                if (releaseDate != null) {
                    try {
                        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = inputFormat.parse(releaseDate);
                        String formattedDate = outputFormat.format(date);
                        row.put("Release Date", formattedDate);
                    } catch (ParseException e) {


                    }
                }

                rows.add(row);
                System.out.println(row); // Imprimir la fila en la consola
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }




    public static void exportToCSV(List<Map<String, String>> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir el encabezado del archivo CSV (nombres de columnas)
            if (!data.isEmpty()) {
                Map<String, String> firstRow = data.get(0);
                for (String columnName : firstRow.keySet()) {
                    writer.write(columnName);
                    writer.write(",");
                }
                writer.write("\n");
            }

            // Escribir los datos
            for (Map<String, String> row : data) {
                for (String value : row.values()) {
                    writer.write(value);
                    writer.write(",");
                }
                writer.write("\n");
            }

            System.out.println("Exportación a CSV exitosa.");
        } catch (IOException e) {
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }


}

