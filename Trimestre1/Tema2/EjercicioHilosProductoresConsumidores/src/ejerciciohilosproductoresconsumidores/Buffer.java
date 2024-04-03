/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciohilosproductoresconsumidores;

/**
 *
 * @author alvar
 */
public class Buffer {
    private final int capacidad;
    private int[] buffer;
    private int contador = 0;
    private int suma = 0;
    private final int valorMaximoSuma;


    public Buffer(int capacidad, int valorMaximoSuma) {
        this.capacidad = capacidad;
        this.buffer = new int[capacidad];
        this.valorMaximoSuma = valorMaximoSuma;

    }

    public synchronized void producir(int valor) {
        while (contador == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        buffer[contador] = valor;
        contador++;
        suma += valor;
        System.out.println("Hilo productor: introduce el valor " + valor + " en la posición " + (contador - 1));
        
        if (suma >= valorMaximoSuma) {
            System.out.println("Valor máximo alcanzado, finalizando hilos.");
            System.exit(0);
        }
        
        notifyAll();
    }

    public synchronized int consumir() {
        while (contador == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        int valor = buffer[contador - 1];
        contador--;
        suma -= valor;
        System.out.println("Hilo consumidor: saca el valor " + valor + " de la posición " + contador);
        
        if (suma >= valorMaximoSuma) {
            System.out.println("Valor máximo alcanzado, finalizando hilos.");
            System.exit(0);
        }
        
        notifyAll();
        return valor;
    }
}

    

