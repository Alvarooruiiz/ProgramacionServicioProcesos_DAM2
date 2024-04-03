/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Cliente.Persona;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alvar
 */
public class MainServidor {

    public static void main(String[] args) throws IOException {
        ConexionServidor con = new ConexionServidor();
        con.crearConexion();

        ArrayList<Persona> lista = con.recibirObjeto();
        ArrayList<Persona> lista20anos = new ArrayList<>();

        for (Persona p : lista) {
            if (p.getEdad() == 20) {
                lista20anos.add(p);
            }
        }
        
        con.enviarObjeto(lista20anos);
        con.terminar();
    }

}
