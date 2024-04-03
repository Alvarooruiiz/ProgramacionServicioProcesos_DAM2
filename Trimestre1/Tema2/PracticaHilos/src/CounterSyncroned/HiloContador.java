/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CounterSyncroned;

/**
 *
 * @author alvar
 */
public class HiloContador extends Thread{
    private int id;
    private Counter counter;
    private int n;

    public HiloContador(int id, Counter counter, int n) {
        this.id = id;
        this.counter = counter;
        this.n = n;
    }
    
    @Override
    public void run(){
        if(counter.isSincronizado()){
            counter.mostrarNumerosSincronizados(id, n);
        }else counter.mostrarNumerosNoSincronizados(id, n);
    }
}
