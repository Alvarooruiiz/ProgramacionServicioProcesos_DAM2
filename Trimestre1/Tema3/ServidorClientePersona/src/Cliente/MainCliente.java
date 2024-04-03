/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class MainCliente {
    public static void main(String[] args) throws IOException {
        ArrayList<Persona> cantera = new ArrayList<>();
        
        Persona p1 = new Persona(1,"Alvaro",21);
        Persona p2 = new Persona(2,"Carlos",22);
        Persona p3 = new Persona(3,"Yeray",20);
        
        cantera.add(p1);
        cantera.add(p2);
        cantera.add(p3);
        
        ConexionCliente con = new ConexionCliente();
        try {
            con.crearConexion();
            con.escribirObjeto(cantera);
            ArrayList<Persona> lista20anos = con.leerObjeto();
            
             for (Persona p : lista20anos) {
                System.out.println(p);
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.getMessage();
        }finally{
            con.terminar();
        }
    }
}
