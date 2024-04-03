/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MediaArray;

/**
 *
 * @author alvar
 */
public class Main {
    public static void main(String[] args) {
        int[] numeros = new int[1000];
        
        for(int i=0; i<numeros.length; i++){
            numeros[i]=i;
        }
        
        MediaHilo mh1 = new MediaHilo(numeros, 3, 500);
        MediaHilo mh2 = new MediaHilo(numeros, 0, 700);

        mh1.start();
        mh2.start();
        
        System.out.println("Acabó");
    }
}
