/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package NumeroOculto;

/**
 *
 * @author alvar
 */
public class Main {

     public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();

        // Crear diez hilos adivinadores
        for (int i = 0; i < 10; i++) {
            new Hilo(i,numeroOculto).start();
        }
    }
}
