/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciohilosproductoresconsumidores;

/**
 *
 * @author alvar
 */
public class EjercicioHilosProductoresConsumidores{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int valorMaximoSuma = 20; // Establece el valor máximo de suma
        Buffer b = new Buffer(10, valorMaximoSuma);
        Consumidor c = new Consumidor(b);
        Productor p1 = new Productor(b);
        Productor p2 = new Productor(b);

        p1.start();
        p2.start();
        c.start();
    }
    
}
