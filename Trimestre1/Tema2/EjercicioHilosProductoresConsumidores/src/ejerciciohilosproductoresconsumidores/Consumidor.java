package ejerciciohilosproductoresconsumidores;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {

    private Buffer buffer;
    private int valorMaximoSuma = 100;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.consumir();
        }
    }

}
