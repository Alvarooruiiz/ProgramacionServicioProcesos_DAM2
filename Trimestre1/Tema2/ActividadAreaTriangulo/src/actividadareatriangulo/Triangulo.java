/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package actividadareatriangulo;

/**
 *
 * @author alvar
 */
public class Triangulo extends Thread{

    private String nombre;
    private int base;
    private int altura;

    public Triangulo(String nombre, int base, int altura) {
        this.nombre = nombre;
        this.base = base;
        this.altura = altura;
    }
    
    public int calcularArea(){
        return (base*altura)/2;
    }
    
    public void run(){
        System.out.println("El area de " + nombre + " es : " + calcularArea());
    }
    
    public static void main(String[] args) {
        Triangulo thread1 = new Triangulo("eduardo",3,2);
        Triangulo thread2 = new Triangulo("malvado",5,5);
        Triangulo thread3 = new Triangulo("chema",4,4);
        Triangulo thread4 = new Triangulo("paquillo",1,23);
        Triangulo thread5 = new Triangulo("jose",3,6);
        Triangulo thread6 = new Triangulo("elena",123,23);
        Triangulo thread7 = new Triangulo("malena",12,2);
        Triangulo thread8 = new Triangulo("diego",7,3);
        Triangulo thread9 = new Triangulo("mauricio",1,1);
        Triangulo thread10 = new Triangulo("osvaldo",6,2);
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        
    }
    
}
