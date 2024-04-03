package abrirNavegadorMostrarHTMLPorPantalla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejemplo {

    public static void main(String[] args) {
        // Especifica la ruta del ejecutable de Opera GX
        String rutaGoogle = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

        // URL de la página web que deseas descargar
        String urlStr = "https://es.wikipedia.org/wiki/Wikipedia:Portada";

        // Comando para abrir Opera GX
        String[] comando = {rutaGoogle,urlStr};

        // Crea una instancia de ProcessBuilder para abrir Opera GX
        ProcessBuilder pb = new ProcessBuilder(comando);        

        try {
            // Inicia el proceso para abrir Opera GX
            pb.start();
            System.out.println("Google Chrome se ha abierto correctamente.");

            // Crea una instancia de URL para la página web
            URL url = new URL(urlStr);

            // Abre una conexión a la URL
            URLConnection connection = url.openConnection();

            // Obtén el flujo de entrada (InputStream) de la conexión
            try (InputStream inputStream = connection.getInputStream()) {
                // Lee el HTML línea por línea
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String linea;
                StringBuilder html = new StringBuilder();

                while ((linea = reader.readLine()) != null) {
                    html.append(linea);
                    html.append("\n");
                }

                // Ahora, el contenido HTML de la página se encuentra en el objeto StringBuilder "html"
                // Puedes imprimirlo o guardarlo en un archivo, por ejemplo
                System.out.println("HTML de la página:");
                System.out.println(html.toString());
            } catch (IOException e) {
                System.err.println("Error al descargar la página: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error al abrir Google Chrome: " + e.getMessage());
        }
    }
}
