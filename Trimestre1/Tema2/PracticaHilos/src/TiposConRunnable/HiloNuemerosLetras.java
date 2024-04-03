/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiposConRunnable;

/**
 *
 * @author alvar
 */
public class HiloNuemerosLetras implements Runnable{
    
    private int tipo;

    public HiloNuemerosLetras(int tipo) {
        this.tipo = tipo;
    }
    
    

    @Override
    public void run() {
        while(true){
            switch(tipo){
                case 1->{
                    for(int i=1; i<30; i++){
                        System.out.println(i);
                    }
                }
                case 2->{
                    for(char c='a'; c<'z'; c++){
                        System.out.println(c);
                    }
                }
                
            }
        }
    }
    
}
