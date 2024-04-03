/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abrirNavegador;

import java.io.IOException;

public class abrirNavegador {
    public static void main(String[] args) {
        String web = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        String destino = "C:\\Users\\alvar\\OneDrive\\Escritorio";

        // Ruta al archivo Java que deseas ejecutar
        String rutaGoogle = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

        try {
            // Crea un objeto ProcessBuilder
            ProcessBuilder builder = new ProcessBuilder(rutaGoogle);

            // Redirige la salida estándar y la salida de error a la consola
            builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            builder.redirectError(ProcessBuilder.Redirect.INHERIT);

            // Inicia el proceso
            Process proceso = builder.start();
            
            // Espera a que el proceso termine (puedes hacer otras operaciones mientras tanto)
            int estado = proceso.waitFor();

            // Imprime el estado de salida del proceso (0 significa éxito)
            System.out.println("El proceso terminó con estado: " + estado);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
