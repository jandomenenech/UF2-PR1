package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;

import java.util.List;



public class Funcions {

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


    public List<Map<String, String>> readCSV(String filePath) {
        List<Map<String, String>> rows = new ArrayList<>();
        try {
            FileReader in = new FileReader(filePath);
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
                            String stringValue = String.valueOf((int) (numericValue * 1000));
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
                        // Manejar errores de conversión de fecha
                        System.err.print("");
                    }
                }

                rows.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }


    public List<Game> readGames(String csvFileName) {
        List<Game> games = new ArrayList<>();
        try {
            FileReader reader = new FileReader(csvFileName);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get(0);
                String title = csvRecord.get(1);
                String releaseDate = "";
                if (csvRecord.get(2) != null) {
                    try {
                        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
                        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = inputFormat.parse(csvRecord.get(2));
                        String formattedDate = outputFormat.format(date);
                        releaseDate = formattedDate;
                    } catch (ParseException e) {
                        // Manejar errores de conversión de fecha
                        System.err.print("");
                    }
                }
                String teamStr = csvRecord.get(3);
                ArrayList<String> team = new ArrayList<>(Arrays.asList(teamStr.split(",")));
                String rating = csvRecord.get(4);
                String timesListed = remplazarK(csvRecord.get(5));
                String numberOfReviews = remplazarK(csvRecord.get(6));
                String genresStr = csvRecord.get(7);
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(genresStr.split(",")));
                String summary = csvRecord.get(8);
                String reviewsStr = csvRecord.get(9);
                ArrayList<String> reviews = new ArrayList<>(Arrays.asList(reviewsStr.split(",")));
                String plays = remplazarK(csvRecord.get(10));
                String playing = remplazarK(csvRecord.get(11));
                String backlogs = remplazarK(csvRecord.get(12));
                String wishlist = remplazarK(csvRecord.get(13));
                Game game = new Game(id, title, releaseDate, team, rating, timesListed, numberOfReviews,
                        genres, summary, reviews, plays, playing, backlogs, wishlist);
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public static void crearArchivoJson(List<Game> game) {
        // Crear una instancia de Gson sin formateo "pretty"
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convertir el objeto a JSON
        String json = gson.toJson(game);

        // Reemplazar las secuencias de escape \u0027 con comillas simples
        String cleanedJson = json.replaceAll("\\\\u0027", "'").replace("\\\n", "");

        // Escribir el JSON formateado a un archivo
        try (FileWriter writer = new FileWriter("miArchivo.json")) {
            writer.write(cleanedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document exportToXML(List<Game> games, String fileName) {
        // Crear un objeto Document y agregar elementos
        Document document = (Document) DocumentHelper.createDocument();
        Element root = (Element) document.addElement("games");

        for (Game game : games) {
            Element gameElement = root.addElement("game");
            gameElement.addElement("id").addText(game.getId());
            gameElement.addElement("title").addText(game.getTitle());
            gameElement.addElement("releaseDate").addText((game.getReleaseDate()));


            Element teamElement = gameElement.addElement("team");
            for (String member : game.getTeam()) {
                teamElement.addElement("member").addText(member);
            }

            gameElement.addElement("rating").addText(game.getRating());
            gameElement.addElement("timesListed").addText(game.getTimesListed());
            gameElement.addElement("numberOfReviews").addText(game.getNumberOfReviews());

            Element genresElement = gameElement.addElement("genres");
            for (String genre : game.getGenres()) {
                genresElement.addElement("genre").addText(genre);
            }

            gameElement.addElement("summary").addText(game.getSummary());

            Element reviewsElement = gameElement.addElement("reviews");
            for (String review : game.getReviews()) {
                reviewsElement.addElement("review").addText(review);
            }
            gameElement.addElement("plays").addText(game.getPlays());
            gameElement.addElement("playing").addText(game.getPlaying());
            gameElement.addElement("backlogs").addText(game.getBacklogs());
            gameElement.addElement("wishlist").addText(game.getWishlist());
        }
        return document;
    }

    public static void writeGameXmlToFile(List<Game> games, String filePath) {
        Document document = exportToXML(games, filePath);

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String remplazarK(String csv) {
        if (csv != null && csv.endsWith("K")) {
            try {
                double numericValue = Double.parseDouble(csv.replace("K", "").trim());
                String stringValue = String.valueOf((int) (numericValue * 1000));
                return stringValue;
            } catch (NumberFormatException e) {
                // Manejar errores de conversión
                System.err.println("Error al convertir valor en clave");
            }
        }
        return "";
    }



}











