/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filosofosconsemaforos;

/**
 *
 * @author alvar
 */
import java.util.concurrent.Semaphore;

public class Mesa {
    private Semaphore[] tenedores;

    public Mesa(int numTenedores) {
        tenedores = new Semaphore[numTenedores];
        for (int i = 0; i < numTenedores; i++) {
            tenedores[i] = new Semaphore(1);
        }
    }

    public void cogerTenedores(int comensal) throws InterruptedException {
        int izquierda = comensal;
        int derecha = (comensal + 1) % tenedores.length;

        // Intenta adquirir el tenedor izquierdo
        tenedores[izquierda].acquire();

        // Intenta adquirir el tenedor derecho
        tenedores[derecha].acquire();
    }

    public void dejarTenedores(int comensal) {
        int izquierda = comensal;
        int derecha = (comensal + 1) % tenedores.length;

        // Libera el tenedor izquierdo
        tenedores[izquierda].release();

        // Libera el tenedor derecho
        tenedores[derecha].release();
    }
}
