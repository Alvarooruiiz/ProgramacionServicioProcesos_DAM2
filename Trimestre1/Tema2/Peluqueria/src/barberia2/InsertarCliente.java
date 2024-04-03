/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barberia2;

/**
 *
 * @author alvar
 */
public class InsertarCliente extends Thread{
    private long tiempoInicial;
    private ColaClientes cola;

    public InsertarCliente(long tiempoInicial, ColaClientes cola) {
        this.tiempoInicial = tiempoInicial;
        this.cola = cola;
    }

    @Override
    public void run() {
        
    }
    
    
    
}
