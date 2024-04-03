package com.mycompany.pruebaimagenapi;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PruebaImagenAPI {

    private static final String PIXABAY_API_URL = "https://pixabay.com/api/?key=42289421-79fd5c37397f8539177b7d0b9&q=yellow+flowers&image_type=photo"; // Reemplaza con tu clave de API

    public static void main(String[] args) {
        try {
            String response = fetchApiResponse();
            String[] imageUrls = extractImageUrls(response);
            for (int i = 0; i < imageUrls.length; i++) {
                String destinationFile = "imagen_" + i + ".jpg";
                downloadImage(imageUrls[i], destinationFile);
                System.out.println("Imagen " + i + " descargada exitosamente.");
            }
        } catch (IOException e) {
            System.out.println("Error al descargar las imágenes: " + e.getMessage());
        }
    }

    private static String fetchApiResponse() throws IOException {
        URL url = new URL(PIXABAY_API_URL);
        try (InputStream in = url.openStream()) {
            return new String(in.readAllBytes());
        }
    }

    private static String[] extractImageUrls(String jsonResponse) {
        Gson gson = new Gson();
        PixabayResponse pixabayResponse = gson.fromJson(jsonResponse, PixabayResponse.class);
        String[] imageUrls = new String[pixabayResponse.hits.length];
        for (int i = 0; i < pixabayResponse.hits.length; i++) {
            imageUrls[i] = pixabayResponse.hits[i].largeImageURL;
        }
        return imageUrls;
    }

    private static void downloadImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static class PixabayResponse {
        Hit[] hits;
    }

    private static class Hit {
        String largeImageURL;
    }
}
