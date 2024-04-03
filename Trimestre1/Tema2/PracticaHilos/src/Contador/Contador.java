/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contador;

/**
 *
 * @author alvar
 */
public class Contador implements Runnable{
    private int contador;
    private String nombre;
    private int limite;

    public Contador(String nombre, int limite) {
        this.nombre = nombre;
        this.limite = limite;
    }

    @Override
    public void run() {
        System.out.println("Comienza" + nombre);
        while(contador<limite){
            System.out.println(nombre + "lleva" +contador);
            contador++;
        }
        System.out.println(nombre + " ha finalizado");
    }
    
    
}
