/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busquedasignificadosrae;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author alvar
 */
public class BusquedaSignificadosRAE {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Instroduzca la palabra a buscar");
        String palabra = sc.nextLine();
        String url2 = "https://dle.rae.es/" + palabra;
        
        URL url = new URL(url2);
        URLConnection conexionURL = url.openConnection();
        HttpURLConnection conexionHttpURL = (HttpURLConnection) conexionURL;
        
        conexionHttpURL.setRequestProperty("User-Agent", "Mozilla/5.0");

        
        try (InputStream is = conexionHttpURL.getInputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(is))) {

            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                respuesta.append(linea).append("\n");
            }

            guardarEnArchivo(respuesta.toString(), "contenido_html.html");


            System.out.println(respuesta.toString());
        }
        
    }
    
    private static void guardarEnArchivo(String contenido, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
