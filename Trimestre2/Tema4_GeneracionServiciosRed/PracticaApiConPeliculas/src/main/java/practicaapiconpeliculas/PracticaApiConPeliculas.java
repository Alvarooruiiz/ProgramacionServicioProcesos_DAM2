package practicaapiconpeliculas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PracticaApiConPeliculas extends JFrame {

    private JTextField movieTitleField;

    private static final String API_KEY = "tu_clave_de_api_aqui"; // Reemplaza con tu clave de API de OMDB

    public PracticaApiConPeliculas() {
        // Configuración de la ventana principal
        setTitle("Movie Info App");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear componentes
        movieTitleField = new JTextField();
        JButton searchButton = new JButton("Buscar");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMovie();
            }
        });

        // Configuración del diseño
        setLayout(new FlowLayout());
        add(new JLabel("Título de la película:"));
        add(movieTitleField);
        add(searchButton);

        // Mostrar la ventana principal
        setVisible(true);
    }

    private void searchMovie() {
        String movieTitle = movieTitleField.getText().trim();
        if (movieTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el título de la película.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String apiUrl = "http://www.omdbapi.com/?apikey=" + API_KEY + "&t=" + movieTitle;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                processMovieInfo(response.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener información de la película. Código de respuesta: " + responseCode, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processMovieInfo(String json) {
        Gson gson = new Gson();
        JsonObject movieObject = JsonParser.parseString(json).getAsJsonObject();

        if (movieObject.has("Error")) {
            String errorMessage = movieObject.get("Error").getAsString();
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear una nueva ventana para mostrar la información de la película
        JFrame movieInfoFrame = new JFrame("Información de la película: " + movieObject.get("Title").getAsString());
        movieInfoFrame.setSize(400, 300);

        // Crear componentes para la ventana secundaria
        JTextArea descriptionTextArea = new JTextArea();
        JTextArea ratingsTextArea = new JTextArea();

        // Configuración del diseño de la ventana secundaria
        movieInfoFrame.setLayout(new GridLayout(2, 1));
        movieInfoFrame.add(new JScrollPane(descriptionTextArea));
        movieInfoFrame.add(new JScrollPane(ratingsTextArea));

        // Procesar la información de la película
        String description = "Título: " + movieObject.get("Title").getAsString() + "\n"
                + "Año: " + movieObject.get("Year").getAsString() + "\n"
                + "Director: " + movieObject.get("Director").getAsString() + "\n"
                + "Género: " + movieObject.get("Genre").getAsString() + "\n"
                + "Sinopsis: " + movieObject.get("Plot").getAsString() + "\n"
                + "Poster: " + movieObject.get("Poster").getAsString();

        String ratings = "Valoraciones:\n";
        if (movieObject.has("Ratings")) {
            for (JsonElement ratingElement : movieObject.getAsJsonArray("Ratings")) {
                JsonObject ratingObject = ratingElement.getAsJsonObject();
                ratings += ratingObject.get("Source").getAsString() + ": " + ratingObject.get("Value").getAsString() + "\n";
            }
        }

        // Establecer el texto en las áreas de texto
        descriptionTextArea.setText(description);
        ratingsTextArea.setText(ratings);

        // Mostrar la ventana secundaria
        movieInfoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PracticaApiConPeliculas();
            }
        });
    }
}
