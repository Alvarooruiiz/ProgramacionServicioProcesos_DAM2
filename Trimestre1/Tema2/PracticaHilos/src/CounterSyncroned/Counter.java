/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CounterSyncroned;

/**
 *
 * @author alvar
 */
public class Counter {
    private boolean sincronizado;

    public Counter(boolean sincronizado) {
        this.sincronizado= sincronizado;
    }
    
    public boolean isSincronizado(){
        return sincronizado;
    }
    
    public synchronized void mostrarNumerosSincronizados(int id, int n){
        System.out.println("Inicio hilo " + id);
        for(int i=0; i<=n; i++){
            System.out.println(i);
        }
        System.out.println("Fin hilo " + id);
    }
    
    public  void mostrarNumerosNoSincronizados(int id, int n){
        System.out.println("Inicio hilo " + id);
        for(int i=0; i<=n; i++){
            System.out.println(i);
        }
        System.out.println("Fin hilo " + id);
    }
    
}
