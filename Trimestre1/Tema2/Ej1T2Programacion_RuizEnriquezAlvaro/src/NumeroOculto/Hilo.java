/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NumeroOculto;

/**
 *
 * @author alvar
 */
public class Hilo extends Thread {
    private final int id;
    private final NumeroOculto numeroOculto;
    public boolean encontrado;

    public Hilo(int id, NumeroOculto numeroOculto) {
        this.id = id;
        this.numeroOculto = numeroOculto;
    }

    @Override
    public void run() {
        
        while (!encontrado) {
            try{
                Thread.sleep((int) (Math.random()*500));
            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
            
            int numeroPropuesto = (int) (Math.random() * 101);
            int resultado = numeroOculto.propuestaNumero(numeroPropuesto);
            switch (resultado) {
                case -1 -> {
                    System.out.println("Juego terminado. Hilo " + id + " se detiene.");
                    encontrado=true;
                }
                case 1 -> {
                    System.out.println("El hilo " + id + " ha adivinado el número y es el: " + numeroPropuesto);
                   encontrado = true;
                }
                default -> System.out.println("Hilo " + id + " falló con el número " + numeroPropuesto);
            }
        }
    }
}
