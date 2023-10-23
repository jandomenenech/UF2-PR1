package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    /*public static ArrayList<Game> readS(String csvFileName) throws Exception {
        ArrayList<Game> games = new ArrayList<>();
        FileReader reader = new FileReader(csvFileName);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        for (CSVRecord csvRecord : csvParser) {
            int id = Integer.parseInt(csvRecord.get(0));
            String title = csvRecord.get(1);
            String releaseDate = csvRecord.get(2);
            String teamStr = csvRecord.get(3);
            ArrayList<String> team = new ArrayList<>(Arrays.asList(teamStr.split(",")));
            double rating = Double.parseDouble(csvRecord.get(4));
            int timesListed = Integer.parseInt(csvRecord.get(5));
            int numberOfReviews = Integer.parseInt(csvRecord.get(6));
            String genresStr = csvRecord.get(7);
            ArrayList<String> genres = new ArrayList<>(Arrays.asList(genresStr.split(",")));
            String summary = csvRecord.get(8);
            String reviewsStr = csvRecord.get(9);
            ArrayList<String> reviews = new ArrayList<>(Arrays.asList(reviewsStr.split(",")));
            int plays = Integer.parseInt(csvRecord.get(10));
            int playing = Integer.parseInt(csvRecord.get(11));
            int backlogs = Integer.parseInt(csvRecord.get(12));
            int wishlist = Integer.parseInt(csvRecord.get(13));

            Game game = new Game(id, title, releaseDate, team, rating, timesListed, numberOfReviews,
                    genres, summary, reviews, plays, playing, backlogs, wishlist);
            games.add(game);
        }

        return games;
    }*/

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
                System.out.println(row); // Imprimir la fila en la consola
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
                int id = Integer.parseInt(csvRecord.get(0));
                String title = csvRecord.get(1);
                String releaseDate = csvRecord.get(2);
                String teamStr = csvRecord.get(3);
                ArrayList<String> team = new ArrayList<>(Arrays.asList(teamStr.split(",")));
                double rating = Double.parseDouble(csvRecord.get(4));
                int timesListed = Integer.parseInt(csvRecord.get(5));
                int numberOfReviews = Integer.parseInt(csvRecord.get(6));
                String genresStr = csvRecord.get(7);
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(genresStr.split(",")));
                String summary = csvRecord.get(8);
                String reviewsStr = csvRecord.get(9);
                ArrayList<String> reviews = new ArrayList<>(Arrays.asList(reviewsStr.split(",")));
                int plays = Integer.parseInt(csvRecord.get(10));
                int playing = Integer.parseInt(csvRecord.get(11));
                int backlogs = Integer.parseInt(csvRecord.get(12));
                int wishlist = Integer.parseInt(csvRecord.get(13));

                Game game = new Game(id, title, releaseDate, team, rating, timesListed, numberOfReviews,
                        genres, summary, reviews, plays, playing, backlogs, wishlist);
                games.add(game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }


    public class XMLExporter {
        public static void exportToXML(List<Game> games, String xmlFileName) {
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                // Crear el elemento raíz
                Element rootElement = document.createElement("games");
                document.appendChild(rootElement);

                // Iterar sobre los juegos y crear elementos para cada uno
                for (Game game : games) {
                    Element gameElement = document.createElement("game");

                    // Agregar atributos o elementos para cada propiedad del juego
                    Element("id", document, game, gameElement);

                    Element titleElement = document.createElement("title");
                    titleElement.appendChild(document.createTextNode(game.getTitle()));
                    gameElement.appendChild(titleElement);


                    rootElement.appendChild(gameElement);
                }

                // Crear un objeto Transformer para escribir el documento XML en un archivo
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File(xmlFileName));

                transformer.transform(source, result);
                System.out.println("Exportación a XML exitosa.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al exportar a XML: " + e.getMessage());
            }
        }
    }


        public static void createXMLFile(ArrayList<Game> games, String xmlFileName) throws Exception {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Games");
            doc.appendChild(rootElement);

        }

        public static void Element(String atributo, Document document, Game game, Element gameElement) {
            Element idElement = document.createElement(atributo);
            idElement.appendChild(document.createTextNode(Integer.toString(game.getId())));
            gameElement.appendChild(idElement);

        }
    }







