package ejerciciohilosproductoresconsumidores;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor extends Thread {
    private Buffer buffer;
    private Random random = new Random();
    private int valorMaximoSuma = 100;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            int valor = random.nextInt(10) + 1; // Valor aleatorio del 1 al 10
            buffer.producir(valor);
        }
    }
}
