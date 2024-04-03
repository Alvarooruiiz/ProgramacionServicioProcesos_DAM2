/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package filosofosconsemaforos;

/**
 *
 * @author alvar
 */
public class Main {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Mesa m = new Mesa(numFilosofos);

        Filosofo[] filosofos = new Filosofo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(m, i);
            filosofos[i].start();
        }
    }
}

