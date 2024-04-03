/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barberia2;

/**
 *
 * @author alvar
 */
public class Barbero extends Thread{
    private String nombre;
    private ColaClientes cola;
    private long tiempoInicial;

    public Barbero(String nombre, ColaClientes cola, long tiempoInicial) {
        this.nombre = nombre;
        this.cola = cola;
        this.tiempoInicial = tiempoInicial;
    }
    
    @Override
    public void run(){
        
    }
}
