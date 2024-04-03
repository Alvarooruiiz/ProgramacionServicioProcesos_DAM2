/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CounterSyncroned;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class Main {
    public static void main(String[] args) {
        boolean sincronizado = true;
        
        Counter c = new Counter(sincronizado);
        
        HiloContador h1 = new HiloContador(1, c, 5);
        HiloContador h2 = new HiloContador(2, c, 10);
        
        h1.start();
        h2.start();
        
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Fin programa");
    }
}
