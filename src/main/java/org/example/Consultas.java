package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.Funcions.writeGameXmlToFile;
import static org.example.Main.pro;

public class Consultas {
    public static void searchGamesByTitle(String title, List<Game> games) {
        List<Game> result = games.stream()
                .filter(game -> game.getTitle().equals(title))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
            System.out.println("Juego encontrado:");
            System.out.println("Como quieres llamar a la consulta");
            String archivo = pro.nextLine();
            String xmlFileName = "src/main/java/org/example/"+archivo;
            writeGameXmlToFile(result, xmlFileName);
        } else {
            System.out.println("No se encontró ningún juego con el título " + title);
        }

    }

    public static void buscarID(String id, List<Game> games) {
        List<Game> result = games.stream()
                .filter(game -> game.getId().equals(id))
                .collect(Collectors.toList());

        if (!result.isEmpty()) {
            System.out.println("Juego encontrado:");
            System.out.println("Como quieres llamar a la consulta");
            String archivo = pro.nextLine();
            String xmlFileName = "src/main/java/org/example/"+archivo;
            writeGameXmlToFile(result, xmlFileName);
        } else {
            System.out.println("No se encontró ningún juego con el título " + id);
        }

    }

    public static String FechaMasNueva(String fechas, List<Game> games) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  // El formato de tu fecha
        Date fecha = null;
        if (fechas != null && !fechas.isEmpty()) {

            try {
                fecha = formato.parse(fechas);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Ahora, 'fecha' es un objeto Date que representa la misma fecha que 'fechaComoString'
        } else {
            System.out.println("La cadena de la fecha está vacía");
        }
        List<Game> recentGames = new ArrayList<>();
        for (Game game : games) {
            try {

                if (game.getReleaseDate() != null) {
                    Date FECHA = formato.parse(game.getReleaseDate());
                    if (FECHA.after(fecha)) {
                        recentGames.add(game);
                    }
                }

            } catch (ParseException e) {
                System.out.print("");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Game game : recentGames) {
            sb.append(game.toString());
            sb.append("\n");  // Agrega una nueva línea entre cada juego
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(recentGames, xmlFileName);
        String recentGamesString = sb.toString();
        return recentGamesString;
    }


    public static String FechaMasAntigua(String fechas, List<Game> games) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  // El formato de tu fecha
        Date fecha = null;
        if (fechas != null && !fechas.isEmpty()) {

            try {
                fecha = formato.parse(fechas);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Ahora, 'fecha' es un objeto Date que representa la misma fecha que 'fechaComoString'
        } else {
            System.out.println("La cadena de la fecha está vacía");
        }
        List<Game> olderGames = new ArrayList<>();
        for (Game game : games) {
            try {

                if (game.getReleaseDate() != null) {
                    Date FECHA = formato.parse(game.getReleaseDate());
                    if (FECHA.before(fecha)) {
                        olderGames.add(game);
                    }
                }

            } catch (ParseException e) {
                System.out.print("");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Game game : olderGames) {
            sb.append(game.toString());
            sb.append("\n");  // Agrega una nueva línea entre cada juego
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(olderGames, xmlFileName);

        String olderGamesString = sb.toString();
        return olderGamesString;
    }


    public static String FechaIgual(String fechas, List<Game> games) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  // El formato de tu fecha
        Date fecha = null;
        if (fechas != null && !fechas.isEmpty()) {

            try {
                fecha = formato.parse(fechas);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Ahora, 'fecha' es un objeto Date que representa la misma fecha que 'fechaComoString'
        } else {
            System.out.println("La cadena de la fecha está vacía");
        }
        List<Game> sameDateGames = new ArrayList<>();
        for (Game game : games) {
            try {

                if (game.getReleaseDate() != null) {
                    Date FECHA = formato.parse(game.getReleaseDate());
                    if (FECHA.equals(fecha)) {
                        sameDateGames.add(game);
                    }
                }

            } catch (ParseException e) {
                System.out.print("");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Game game : sameDateGames) {
            sb.append(game.toString());
            sb.append("\n");  // Agrega una nueva línea entre cada juego
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(sameDateGames, xmlFileName);
        String sameDateGamesString = sb.toString();
        return sameDateGamesString;
    }

    public static List<Game> juegosConMiembroDelEquipo(String nombreUsuario, List<Game> games) {
        List<Game> juegosConMiembroDelEquipo = new ArrayList<>();
        for (Game game : games) {
            ArrayList<String> team = game.getTeam();
            if (team.contains(nombreUsuario)) {
                juegosConMiembroDelEquipo.add(game);
            }
        }
        return juegosConMiembroDelEquipo;
    }


    public static List<Game> juegosConRating(String ratingUsuario, List<Game> games) {
        List<Game> juegosConRating = new ArrayList<>();
        for (Game game : games) {
            if (game.getRating().equals(ratingUsuario)) {
                juegosConRating.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosConRating, xmlFileName);
        return juegosConRating;
    }
    public static List<Game> reviws(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        for (Game game : games) {
            if (game.getNumberOfReviews().equals(ratingUsuario)) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> reviewsSuperior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getNumberOfReviews());
            if (gameReviews > ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> reviewsInferior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getNumberOfReviews());
            if (gameReviews < ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }
    public static List<Game> plays(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        for (Game game : games) {
            if (game.getPlays().equals(ratingUsuario)) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> playsSuperior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getPlays());
            if (gameReviews > ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> playsInferior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getPlays());
            if (gameReviews < ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }
    public static List<Game> players(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        for (Game game : games) {
            if (game.getPlaying().equals(ratingUsuario)) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> playersSuperior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getPlaying());
            if (gameReviews > ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> playersInferior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getPlaying());
            if (gameReviews < ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }
    public static List<Game> Listed(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        for (Game game : games) {
            if (game.getTimesListed().equals(ratingUsuario)) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> listedSuperior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getTimesListed());
            if (gameReviews > ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

    public static List<Game> listedInferior(String ratingUsuario, List<Game> games) {
        List<Game> juegosRevi = new ArrayList<>();
        int ratingUsuarioInt = Integer.parseInt(ratingUsuario);
        for (Game game : games) {
            int gameReviews = Integer.parseInt(game.getTimesListed());
            if (gameReviews < ratingUsuarioInt) {
                juegosRevi.add(game);
            }
        }
        System.out.println("Como quieres llamar a la consulta");
        String archivo = pro.nextLine();
        String xmlFileName = "src/main/java/org/example/"+archivo;
        writeGameXmlToFile(juegosRevi, xmlFileName);
        return juegosRevi;
    }

}


