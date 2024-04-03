/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejemploapipeliculasconcorreo;

import java.util.Scanner;

/**
 *
 * @author alvar
 */
public class EjemploApiPeliculasConCorreo {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n=-1;
        while(n!=0){
            switch(n){
                case 1->{
                    System.out.println("Introduzca el id de la imagen a descargar");
                    int idFoto=sc.nextInt();
                    GestorApi g = new GestorApi();
                }
                case 2->{
                    System.out.println("Estos son todos los datos de la api");
                    GestorApi g = new GestorApi();
                    String url = "https://pixabay.com/api/?key=42289421-79fd5c37397f8539177b7d0b9&q=yellow+flowers&image_type=photo";
                    String contenido = g.getContenidoMetodoGet(url).toString();
                    System.out.println(contenido);
                }
                case 3->{
                    
                }
            }
        }
        
        
        System.out.println("https://pixabay.com/api/?key=42289421-79fd5c37397f8539177b7d0b9&q=yellow+flowers&image_type=photo");
    }
}
