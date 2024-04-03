/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package busquedaimdbconexionhttpurl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author alvar
 */
public class BusquedaIMDBConexionHttpURL {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca el nombre de la pelicula");
            String nombre = sc.nextLine();
            nombre = nombre.replaceAll(" ", "+");
            
            String esquema = "https://";
            String servidor = "www.imdb.com/";
            String path = "/find/";
            String recurso = URLEncoder.encode(nombre, StandardCharsets.UTF_8.name());
            String parametros = "?q=" + recurso;
            
            BusquedaIMDBConexionHttpURL gp = new BusquedaIMDBConexionHttpURL();
            String direccion = esquema + servidor + path + parametros;
            StringBuilder resultado = gp.getContenidoMetodoGet(direccion);
            BusquedaIMDBConexionHttpURL.writeFile("pelicula.html", resultado.toString());
            System.out.println("Descarga finalizada");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public StringBuilder getContenidoMetodoGet(String direccion) throws Exception {

        StringBuilder respuesta = new StringBuilder();
        URL url = new URL(direccion);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type", "text-plain");
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

    public static void writeFile(String strPath, String contenido) throws IOException {

        Path path = Paths.get(strPath);
        byte[] strToBytes = contenido.getBytes();
        Files.write(path, strToBytes);
    }
}
