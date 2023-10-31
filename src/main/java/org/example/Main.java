package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.Consultas.*;
import static org.example.Funcions.*;

public class Main {
    public static List<Game> games;
    public static Scanner pro = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, ParseException {
        Funcions csvReader = new Funcions();
        String inputCsvFileName = "src/main/java/org/example/Games.txt";
        games = csvReader.readGames(inputCsvFileName);
        String opcion = "";
        while (!opcion.equalsIgnoreCase("6") && !opcion.equalsIgnoreCase("salir")) {
            System.out.println("Elije una opcion");
            System.out.println("1.Exportar");
            System.out.println("2.Consultas");
            System.out.println("6.Salir");
            opcion = pro.nextLine();
            switch (opcion) {
                case "1", "exportar":
                    System.out.println("Elije otra opcion");
                    System.out.println("1.CSV");
                    System.out.println("2.JSON");
                    System.out.println("3.XML");
                    System.out.println("4.Salir");
                    String exportar = pro.nextLine();
                    switch (exportar) {
                        case "1", "csv":
                            inputCsvFileName = "src/main/java/org/example/Games.txt";
                            String outputCsvFileName = "src/main/java/org/example/ExportedGame.csv";
                            List<Map<String, String>> inputData = csvReader.readCSV(inputCsvFileName);
                            exportToCSV(inputData, outputCsvFileName);

                            break;
                        case "2", "json":
                            crearArchivoJson(games);
                            break;
                        case "3", "xml":
                            String xmlFileName = "src/main/java/org/example/Eso.xml;";
                            csvReader.writeGameXmlToFile(games, xmlFileName);
                            System.out.println("Archivo xml Creado");
                            break;
                        case "4", "atras":
                            break;
                        default:
                            System.err.println("Opcion incorrecta");
                            break;
                    }
                    break;
                case "2", "consultas":
                    System.out.println("Sobre que quieres buscar");
                    System.out.println();
                    System.out.println("1.Id");
                    System.out.println("2.Titulo");
                    System.out.println("3.Fecha");
                    System.out.println("4.Rating");
                    System.out.println("5.Numero de reviws");
                    System.out.println("6.Jugadas");
                    System.out.println("7.Jugadores");
                    System.out.println("8.Times Listes");
                    System.out.println("9.Volver");
                    int consulta = pro.nextInt();
                    pro.nextLine();
                    switch (consulta) {
                        case 1:
                            System.out.println("Pon el ID del juego");
                            String id = pro.nextLine();
                            buscarID(id, games);
                            break;
                        case 2:
                            System.out.println("Que juego quieres buscar");
                            String titulo = pro.nextLine();
                            searchGamesByTitle(titulo, games);
                            break;
                        case 3:
                            System.out.println("Quieres que sea mas ANTIGUO, IGUAL, o mas NUEVO");
                            String eleccion = pro.nextLine().trim();
                            if (eleccion.equalsIgnoreCase("antiguo")) {
                                System.out.println("Dime una fecha en formato (dd/MM/yyyy)");
                                String date = pro.nextLine();
                                FechaMasAntigua(date, games);
                            } else if (eleccion.equalsIgnoreCase("nuevo")) {
                                System.out.println("Dime una fecha en formato (dd/MM/yyyy)");
                                String date = pro.nextLine();
                                FechaMasNueva(date, games);
                            } else {
                                System.out.println("Has elegido igual");
                                System.out.println("Dime una fecha en formato (dd/MM/yyyy)");
                                String date = pro.nextLine();
                                FechaIgual(date, games);
                            }

                            break;

                        case 4:
                            System.out.println("Busca los rating iguales al introducido");
                            String rating = pro.nextLine();
                            juegosConRating(rating, games);
                            break;

                        case 5:
                            System.out.println("Quieres que sea mas igual, superior, o mas inferior");
                            eleccion = pro.nextLine().trim();
                            if (eleccion.equalsIgnoreCase("inferior")) {
                                System.out.println("Dime un numero de reviws");
                                String revi = pro.nextLine();
                                reviewsInferior(revi, games);
                            } else if (eleccion.equalsIgnoreCase("superior")) {
                                System.out.println("Dime un numero de reviws");
                                String revi = pro.nextLine();
                                reviewsSuperior(revi, games);
                            } else {
                                System.out.println("Busca los numero de reviws al introducido");
                                String revi = pro.nextLine();
                                reviws(revi,games);
                            }

                            break;
                        case 6:
                            System.out.println("Quieres que sea mas igual, superior, o mas inferior");
                            eleccion = pro.nextLine().trim();
                            if (eleccion.equalsIgnoreCase("inferior")) {
                                System.out.println("Dime un numero de plays");
                                String revi = pro.nextLine();
                                playsInferior(revi, games);
                            } else if (eleccion.equalsIgnoreCase("superior")) {
                                System.out.println("Dime un numero de plays");
                                String revi = pro.nextLine();
                                playsSuperior(revi, games);
                            } else {
                                System.out.println("Busca los numero de plays al introducido");
                                String revi = pro.nextLine();
                                plays(revi,games);
                            }
                            break;
                        case 7:
                            System.out.println("Quieres que sea mas igual, superior, o mas inferior");
                            eleccion = pro.nextLine().trim();
                            if (eleccion.equalsIgnoreCase("inferior")) {
                                System.out.println("Dime un numero de playing");
                                String revi = pro.nextLine();
                                playersInferior(revi, games);
                            } else if (eleccion.equalsIgnoreCase("superior")) {
                                System.out.println("Dime un numero de playing");
                                String revi = pro.nextLine();
                                playersSuperior(revi, games);
                            } else {
                                System.out.println("Busca los numero de playing al introducido");
                                String revi = pro.nextLine();
                                players(revi,games);
                            }
                            break;
                        case 8:
                            System.out.println("Quieres que sea mas igual, superior, o mas inferior");
                            eleccion = pro.nextLine().trim();
                            if (eleccion.equalsIgnoreCase("inferior")) {
                                System.out.println("Dime un numero de timesListed");
                                String revi = pro.nextLine();
                                listedInferior(revi, games);
                            } else if (eleccion.equalsIgnoreCase("superior")) {
                                System.out.println("Dime un numero de timesListed");
                                String revi = pro.nextLine();
                                listedSuperior(revi, games);
                            } else {
                                System.out.println("Busca los numero de timesListed al introducido");
                                String revi = pro.nextLine();
                                Listed(revi,games);
                            }
                            break;
                        case 9:
                            System.out.println("Saliendo...");
                            Thread.sleep(1000);
                            break;
                        default:
                            System.err.println("Opcion incorrecta");

                    }
                    break;

                case "6", "salir":
                    System.out.println("adeu");
                    break;
                default:
                    System.err.println("Opcion incorrecta");
                    break;

            }
        }


    }


}






