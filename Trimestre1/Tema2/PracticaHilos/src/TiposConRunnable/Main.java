/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiposConRunnable;

/**
 *
 * @author alvar
 */
public class Main {
    public static void main(String[] args) {
        HiloNuemerosLetras h1 = new HiloNuemerosLetras(1);
        HiloNuemerosLetras h2    = new HiloNuemerosLetras(2);
        
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        
        t1.start();
        t2.start();
    }
}
