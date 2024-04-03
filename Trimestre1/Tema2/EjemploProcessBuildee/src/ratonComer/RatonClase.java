/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ratonComer;

import abrirNavegador.abrirNavegador;

public class RatonClase{
    String nombre;
    int tiempoAlimentacion;
    
    public RatonClase (String nombre, int tiempoAlimentacion){
        super();
        this.nombre = nombre;
        this.tiempoAlimentacion=tiempoAlimentacion;
    }
    
    public void comer(){
        try{
            System.out.printf("El raton %s ha comenzado a alimentarse%n",nombre);
            Thread.sleep(tiempoAlimentacion * 1000);
            System.out.printf("El raton ha terminado de alimentarse ",nombre);
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        RatonClase fievel = new RatonClase("Fievel",4);
        RatonClase jerry = new RatonClase("Jerry",5);
        RatonClase pinky = new RatonClase("Pinky",3);
        RatonClase mickey = new RatonClase("Mickey",6);
        fievel.comer();
        jerry.comer();
        pinky.comer();
        mickey.comer();
        
    }
}
