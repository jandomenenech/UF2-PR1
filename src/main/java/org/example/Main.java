package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.Funcions.exportToCSV;

public class Main {
    public static Scanner pro = new Scanner(System.in);
    public static void main(String[] args) {
        Funcions csvReader = new Funcions();
        System.out.println("Elije una opcion");
        System.out.println("1.Exportar");
        System.out.println("2.Consultas");
        System.out.println("3.Salir");
        String opcion = pro.nextLine();
        switch (opcion){
            case "1","exportar":
                System.out.println("Elije otra opcion");
                String exportar = pro.nextLine();
                switch (exportar){
                    case "1","csv":
                                String inputCsvFileName = "src/Games.txt";
                                String outputCsvFileName = "ExportedGame.csv";
                                List<Map<String, String>> inputData = csvReader.readCSV(inputCsvFileName);
                                List<Game> games = csvReader.readGames(inputCsvFileName);
                                exportToCSV(inputData, outputCsvFileName);

                        break;
                    case "2","json":
                        break;
                    case "3","xml":
                        String csvFileName = "src/ExportedGames.csv";
                        String xmlFileName = "src/Eso.txt;";
                        /*try {
                            ArrayList<Game> games = Funcions.CSVToXMLConverter.readS(csvFileName);
                            Funcions.CSVToXMLConverter.createXMLFile(games, xmlFileName);
                            System.out.println("CSV to XML conversion completed successfully.");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.err.println("An error occurred during CSV to XML conversion.");
                        }*/
                        break;
                    case "4","salir":
                        break;
                    default:
                        System.err.println("Opcion incorrecta");
                        break;
                }
                break;
            case "2","consultas":
                break;
            case "3","salir":
                break;
            default:
                System.err.println("Opcion incorrecta");
                break;

        }


        }

}


