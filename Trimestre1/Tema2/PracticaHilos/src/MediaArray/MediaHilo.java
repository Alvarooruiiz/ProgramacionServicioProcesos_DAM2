/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaArray;

/**
 *
 * @author alvar
 */
public class MediaHilo extends Thread{

    private double resultado;
    private int[] array;
    private int inicio;
    private int fin;

    public MediaHilo(int[] array, int inicio, int fin) {
        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    public double getResultado() {
        return resultado;
    }

    
    
    
    @Override
    public void run() {
        int suma=0;
        for(int i=inicio; i<fin; i++){
            suma+=array[i];
        }
        
        this.resultado =(double) suma/(fin-inicio);
    }
    
}
