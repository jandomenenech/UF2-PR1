package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.Funcions.*;

public class Main {
    public static List<Game> games;
    public static Scanner pro = new Scanner(System.in);
    public static void main(String[] args) {
        Funcions csvReader = new Funcions();
        String inputCsvFileName = "src/main/java/org/example/Games.txt";
        games = csvReader.readGames(inputCsvFileName);
        String opcion = "";
        while (!opcion.equalsIgnoreCase("3")||!opcion.equalsIgnoreCase("salir")){
        System.out.println("Elije una opcion");
        System.out.println("1.Exportar");
        System.out.println("2.Consultas");
        System.out.println("3.Salir");
        opcion = pro.nextLine();
        switch (opcion){
            case "1","exportar":
                System.out.println("Elije otra opcion");
                String exportar = pro.nextLine();
                switch (exportar){
                    case "1","csv":
                        inputCsvFileName = "src/main/java/org/example/Games.txt";
                        String outputCsvFileName = "src/main/java/org/example/ExportedGame.csv";
                        List<Map<String, String>> inputData = csvReader.readCSV(inputCsvFileName);
                        exportToCSV(inputData, outputCsvFileName);

                        break;
                    case "2","json":
                        crearArchivoJson(games);
                        break;
                    case "3","xml":
                        String csvFileName = "src/main/java/org/example/ExportedGames.csv";
                        String xmlFileName = "src/main/java/org/example/Eso.xml;";
                        for (Game game : games) {

                            csvReader.writeGameXmlToFile(game, xmlFileName);
                                }
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

}




