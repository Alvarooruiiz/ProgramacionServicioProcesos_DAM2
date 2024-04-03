/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaexamenapiimagen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alvar
 */
public class GestorApi {

    public void accederApi(String url) {
        try {
            String esquema = "https://";
            String servidor = url;
            String direccion = esquema + servidor;
            StringBuilder resultado = conexionHTTP.getContenidoMetodoGet(direccion);

            System.out.println(resultado);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public StringBuilder getContenidoMetodoGet(String direccion) throws Exception {

        StringBuilder respuesta = new StringBuilder();
        URL url = new URL(direccion);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type", "application/json");
        conexion.setRequestProperty("charset", "utf-8");
        conexion.setRequestProperty("User-Agent", "Mozilla/5.0");
        int estado = conexion.getResponseCode();

        Reader streamReader = null;
        if (estado == HttpURLConnection.HTTP_OK) {

            streamReader = new InputStreamReader(conexion.getInputStream());
            int caracter;
            while ((caracter = streamReader.read()) != -1) {
                respuesta.append((char) caracter);
            }

        } else {
            throw new Exception("Error HTTP " + estado);
        }

        conexion.disconnect();
        return respuesta;
    }

    public String formatJSON(String rawJSON) {
        StringBuilder formattedJSON = new StringBuilder();

    try {
        JSONObject jsonObject = new JSONObject(rawJSON);

        formattedJSON.append("-------------Rick y Morty-------------\n");
        formattedJSON.append("ID: ").append(jsonObject.getInt("id")).append("\n");
        formattedJSON.append("Nombre: ").append(jsonObject.getString("name")).append("\n");
        formattedJSON.append("Estado: ").append(jsonObject.getString("status")).append("\n");
        formattedJSON.append("Especie: ").append(jsonObject.getString("species")).append("\n");
        formattedJSON.append("Tipo: ").append(jsonObject.getString("type")).append("\n");
        formattedJSON.append("Género: ").append(jsonObject.getString("gender")).append("\n");
        formattedJSON.append("\n\n");
        
    } catch (JSONException e) {
        e.printStackTrace();
        formattedJSON.append("Error al procesar el JSON: ").append(e.getMessage());
    }

    return formattedJSON.toString();
    }

    public void descargarImagen(String imageUrl, String destinationFile) {
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // Establecer el User-Agent para evitar errores 403

            // Leer los bytes de la imagen y guardarlos en un archivo
            try ( InputStream inputStream = connection.getInputStream();  OutputStream outputStream = new FileOutputStream(destinationFile)) {
                byte[] buffer = new byte[2048]; 
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                System.out.println("Imagen descargada con éxito: " + destinationFile);

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al descargar la imagen: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al abrir la conexión: " + e.getMessage());
        }
    }
    
    public String obtenerValorPorId(String contenidoJSON, String campo, String id) {
    try {
        JSONObject jsonObject = new JSONObject(contenidoJSON);
        // Comprobamos si el JSON contiene un array de resultados
        if (jsonObject.has("results")) {
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            // Iteramos sobre los resultados para encontrar el que coincida con el ID
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject result = resultsArray.getJSONObject(i);
                if (result.getInt("id") == Integer.parseInt(id)) {
                    return result.getString(campo);
                }
            }
        } else { // Si no hay un array de resultados, buscamos directamente en el objeto principal
            if (jsonObject.getInt("id") == Integer.parseInt(id)) {
                return jsonObject.getString(campo);
            }
        }
        // Si no se encuentra el ID, se devuelve un mensaje de error
        return "No se encontró ningún elemento con el ID proporcionado";
    } catch (JSONException e) {
        e.printStackTrace();
        return "Error al procesar el JSON: " + e.getMessage();
    }
}
}
