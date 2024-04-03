/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.exament4apicorreo_ruizenriquezalvaro;

import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author alvar
 */
public class ExamenT4ApiCorreo_RuizEnriquezAlvaro_1 {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String id = 15+ "";

            GestorApi g = new GestorApi();
            String url = "https://fakestoreapi.com/products/" + id;

            String contenidoApi = g.getContenidoMetodoGet(url).toString();

            System.out.println("\nJSON formateado:");
            String formated = g.formatJSON(contenidoApi);
            System.out.println(formated);

            JSONObject jsonObject = new JSONObject(contenidoApi);
            String titulo = jsonObject.getString("title");
            String descripcion = jsonObject.getString("description");
            String categoria = jsonObject.getString("category");
            Double precio = jsonObject.getDouble("price");
            String imageUrl = jsonObject.getString("image");
            g.descargarImagen(imageUrl, "imagen.jpg");

            GestorEmail email = new GestorEmail();
            String emailEmisor = "alvaro.ruiz.enrique@gmail.com";
            String passwordEmisor = "likt hjet gkav gteb";
            
            email.enviarMensajeConAdjunto(emailEmisor, "alvaro.ruiz.enriquez@hotmail.com",
                    "Examen T4 " + titulo,"Descripcion: " + descripcion + "\n" + "Categoria: " + categoria + "\n" + "Precio: " + precio + "€",
                    emailEmisor, passwordEmisor, "imagen.jpg");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }

}
