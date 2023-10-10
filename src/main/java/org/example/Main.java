package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
            // Datos que deseas guardar en disco
            String nombre = "Juan";
            int edad = 30;

            // Especifica la ruta del archivo donde deseas guardar los datos
            String rutaArchivo = "datos.dat";

            try {
                // Crea un FileOutputStream para escribir en el archivo
                FileOutputStream archivoSalida = new FileOutputStream(rutaArchivo);

                // Crea un ObjectOutputStream para escribir objetos en el archivo
                ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);

                // Escribe los datos en el archivo
                objetoSalida.writeObject(nombre);
                objetoSalida.writeObject(edad);

                // Cierra el ObjectOutputStream
                objetoSalida.close();

                System.out.println("Datos guardados en " + rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}


