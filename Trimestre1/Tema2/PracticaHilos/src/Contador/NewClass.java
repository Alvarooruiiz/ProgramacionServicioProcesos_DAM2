//import java.util.Random;
//
//class NumeroOculto {
//    private int numeroOculto;
//    private boolean juegoTerminado;
//
//    public NumeroOculto() {
//        Random random = new Random();
//        this.numeroOculto = random.nextInt(101); // Número aleatorio entre 0 y 100
//        this.juegoTerminado = false;
//    }
//
//    public synchronized int propuestaNumero(int num) {
//        if (juegoTerminado) {
//            return -1; // El juego ha terminado
//        } else if (num == numeroOculto) {
//            juegoTerminado = true;
//            return 1; // El número fue adivinado correctamente
//        } else {
//            return 0; // El número no es el correcto
//        }
//    }
//}
//
//class HiloAdivinador extends Thread {
//    private NumeroOculto numeroOculto;
//
//    public HiloAdivinador(NumeroOculto numeroOculto) {
//        this.numeroOculto = numeroOculto;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            int numeroPropuesto = (int) (Math.random() * 101); // Número aleatorio entre 0 y 100
//            int resultado = numeroOculto.propuestaNumero(numeroPropuesto);
//
//            if (resultado == -1) {
//                System.out.println("Juego terminado. Hilo " + Thread.currentThread().getId() + " se detiene.");
//                break;
//            } else if (resultado == 1) {
//                System.out.println("¡Hilo " + Thread.currentThread().getId() + " ha adivinado el número!");
//                break;
//            }
//        }
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        NumeroOculto numeroOculto = new NumeroOculto();
//
//        // Crear diez hilos adivinadores
//        HiloAdivinador[] hilos = new HiloAdivinador[10];
//        for (int i = 0; i < 10; i++) {
//            hilos[i] = new HiloAdivinador(numeroOculto);
//            hilos[i].start();
//        }
//
//        // Esperar a que todos los hilos terminen
//        for (int i = 0; i < 10; i++) {
//            try {
//                hilos[i].join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}