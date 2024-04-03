package com.mycompany.ejercicio5_3;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio5_3 {

    private static final String ALGORITMO = "AES";
    private static final String TRANSFORMACION = "AES";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione la opción que desea realizar:");
        System.out.println("1. Encriptar archivo");
        System.out.println("2. Desencriptar archivo");
        System.out.println("---------------------------------------");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                System.out.println("Ingrese la ruta del archivo a encriptar:");
                String archivoEntradaEncriptar = scanner.nextLine();
                System.out.println("Ingrese la contraseña:");
                String contrasenaEncriptar = scanner.nextLine();
                try {
                    encriptarArchivo(archivoEntradaEncriptar, contrasenaEncriptar);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                System.out.println("Ingrese la ruta del archivo a desencriptar:");
                String archivoEntradaDesencriptar = scanner.nextLine();
                System.out.println("Ingrese la contraseña:");
                String contrasenaDesencriptar = scanner.nextLine();
                try {
                    desencriptarArchivo(archivoEntradaDesencriptar, contrasenaDesencriptar);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            default ->
                System.out.println("Opción no válida.");
        }
    }

    private static void encriptarArchivo(String archivoEntrada, String contra) throws Exception {
        File archivoEntradaFile = new File(archivoEntrada);

        byte[] bytesEntrada = Files.readAllBytes(Paths.get(archivoEntrada));

        Key clave = generarClave(contra);
        Cipher cifrador = Cipher.getInstance(TRANSFORMACION);
        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        byte[] bytesSalida = cifrador.doFinal(bytesEntrada);

        String archivoSalida =  "encriptado_" + archivoEntrada;
        try ( FileOutputStream outputStream = new FileOutputStream(archivoSalida)) {
            outputStream.write(bytesSalida);
        }

        archivoEntradaFile.delete();
        System.out.println("Encriptación realizada con éxito");
    }

    private static void desencriptarArchivo(String archivoEntrada, String contra) throws Exception {
        File archivoEntradaFile = new File(archivoEntrada);

        byte[] bytesEntrada = Files.readAllBytes(Paths.get(archivoEntrada));

        Key clave = generarClave(contra);
        Cipher cifrador = Cipher.getInstance(TRANSFORMACION);
        cifrador.init(Cipher.DECRYPT_MODE, clave);

        byte[] bytesSalida = cifrador.doFinal(bytesEntrada);

        String archivoSalida = archivoEntrada.replace("encriptado_", "desencriptado_");
        try ( FileOutputStream outputStream = new FileOutputStream(archivoSalida)) {
            outputStream.write(bytesSalida);
        }

        archivoEntradaFile.delete();

        System.out.println("Desencriptación realizada con exito");
    }

    private static Key generarClave(String contrasena) throws Exception {
        byte[] bytesClave = Arrays.copyOf(contrasena.getBytes(), 16);
        return new SecretKeySpec(bytesClave, ALGORITMO);
    }
}
