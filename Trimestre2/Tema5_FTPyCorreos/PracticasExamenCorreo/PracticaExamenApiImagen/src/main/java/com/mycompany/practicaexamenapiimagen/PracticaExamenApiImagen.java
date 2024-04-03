/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.practicaexamenapiimagen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alvar
 */
public class PracticaExamenApiImagen {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce la ID del personaje que deseas descargar: ");
            String characterId = scanner.nextLine();

            GestorApi g = new GestorApi();
            String url = "https://rickandmortyapi.com/api/character/" + characterId;

            System.out.println("Contenido de la API:");
            String contenidoApi = g.getContenidoMetodoGet(url).toString();
            System.out.println(contenidoApi);
            String nombrePersonaje = g.obtenerValorPorId(contenidoApi, "name", characterId);
            System.out.println("Nombre del personaje: " + nombrePersonaje);

            System.out.println("\nJSON formateado:");
            String formated = g.formatJSON(contenidoApi);
            System.out.println(formated);

            // Descargar la imagen correspondiente al personaje seleccionado
            JSONObject jsonObject = new JSONObject(contenidoApi);
            String imageUrl = jsonObject.getString("image");
            g.descargarImagen(imageUrl, "imagen1.jpg");

//            GestorEmail email = new GestorEmail();
//            String emailEmisor = "alvaro.ruiz.enrique@gmail.com";  // Cuenta Gmail completa de emisor
//            String passwordEmisor = "likt hjet gkav gteb";
//            email.enviarMensajeConAdjunto(emailEmisor, "alvaro.ruiz.enriquez@hotmail.com", "Practica", formated, emailEmisor, passwordEmisor, "imagen1.jpg");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
