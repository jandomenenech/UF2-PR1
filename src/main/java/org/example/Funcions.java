package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.dom4j.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;
import java.io.File;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


public class Funcions {
    public static Funcions CSVToXMLConverter;


    /*public List<Map<String, String>> readCSV(String filePath) {
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


                    }
                }

                rows.add(row);
                System.out.println(row); // Imprimir la fila en la consola
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }*/


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

    public static ArrayList<Game> readS(String csvFileName) throws Exception {
        ArrayList<Game> games = new ArrayList<>();
        FileReader reader = new FileReader(csvFileName);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        for (CSVRecord csvRecord : csvParser) {
            String id = csvRecord.get(0);
            String title = csvRecord.get(1);
            String releaseDate = csvRecord.get(2);
            String teamStr = csvRecord.get(3);
            ArrayList<String> team = new ArrayList<>(Arrays.asList(teamStr.split(",")));
            String rating = csvRecord.get(4);
            String timesListed = csvRecord.get(5);
            String numberOfReviews = csvRecord.get(6);
            String genresStr = csvRecord.get(7);
            ArrayList<String> genres = new ArrayList<>(Arrays.asList(genresStr.split(",")));
            String summary = csvRecord.get(8);
            String reviewsStr = csvRecord.get(9);
            ArrayList<String> reviews = new ArrayList<>(Arrays.asList(reviewsStr.split(",")));
            String plays = csvRecord.get(10);
            String playing = csvRecord.get(11);
            String backlogs = csvRecord.get(12);
            String wishlist = csvRecord.get(13);

            Game game = new Game(id, title, releaseDate, team, rating, timesListed, numberOfReviews,
                    genres, summary, reviews, plays, playing, backlogs, wishlist);
            games.add(game);
        }

        return games;
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
                        System.err.println("Error al convertir fecha en clave Release Date");
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
                String releaseDate = csvRecord.get(2);
                String teamStr = csvRecord.get(3);
                ArrayList<String> team = new ArrayList<>(Arrays.asList(teamStr.split(",")));
                String rating = csvRecord.get(4);
                String timesListed = csvRecord.get(5);
                String numberOfReviews = csvRecord.get(6);
                String genresStr = csvRecord.get(7);
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(genresStr.split(",")));
                String summary = csvRecord.get(8);
                String reviewsStr = csvRecord.get(9);
                ArrayList<String> reviews = new ArrayList<>(Arrays.asList(reviewsStr.split(",")));
                String plays = csvRecord.get(10);
                String playing = csvRecord.get(11);
                String backlogs = csvRecord.get(12);
                String wishlist = csvRecord.get(13);

                Game game = new Game(id, title, releaseDate, team, rating, timesListed, numberOfReviews,
                        genres, summary, reviews, plays, playing, backlogs, wishlist);
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    public static Document createGameXml(Game game) {
        Document document = (Document) DocumentHelper.createDocument();
        Element rootElement = (Element) document.createElement("game");

        rootElement.addElement("id").setText(String.valueOf(game.getId()));
        rootElement.addElement("title").setText(game.getTitle());
        rootElement.addElement("releaseDate").setText(game.getReleaseDate());

        // Agregar la lista de equipos
        Element teamElement = rootElement.addElement("team");
        for (String teamMember : game.getTeam()) {
            teamElement.addElement("member").setText(teamMember);
        }

        rootElement.addElement("rating").setText(String.valueOf(game.getRating()));
        rootElement.addElement("timesListed").setText(String.valueOf(game.getTimesListed()));
        rootElement.addElement("numberOfReviews").setText(String.valueOf(game.getNumberOfReviews()));

        // Agregar la lista de géneros
        Element genresElement = rootElement.addElement("genres");
        for (String genre : game.getGenres()) {
            genresElement.addElement("genre").setText(genre);
        }

        rootElement.addElement("summary").setText(game.getSummary());

        // Agregar la lista de revisiones
        Element reviewsElement = rootElement.addElement("reviews");
        for (String review : game.getReviews()) {
            reviewsElement.addElement("review").setText(review);
        }

        rootElement.addElement("plays").setText(String.valueOf(game.getPlays()));
        rootElement.addElement("playing").setText(String.valueOf(game.getPlaying()));
        rootElement.addElement("backlogs").setText(String.valueOf(game.getBacklogs()));
        rootElement.addElement("wishlist").setText(String.valueOf(game.getWishlist()));

        return document;
    }



    public static void writeGameXmlToFile(Game game, String filePath) {
            Document document = createGameXml(game);

            try {
                OutputFormat format = OutputFormat.createPrettyPrint();
                XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
                writer.write(document);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static void crearArchivoJson(List<Game> game) {
        // Crear una instancia de Gson sin formateo "pretty"
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convertir el objeto a JSON
        String json = gson.toJson(game);

        // Reemplazar las secuencias de escape \u0027 con comillas simples
        String cleanedJson = json.replaceAll("\\\\u0027", "'");

        // Escribir el JSON formateado a un archivo
        try (FileWriter writer = new FileWriter("miArchivo.json")) {
            writer.write(cleanedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}








