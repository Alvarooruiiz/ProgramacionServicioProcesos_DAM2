package NumeroOculto;

/**
 *
 * @author alvar
 */
public class NumeroOculto {
    private int numeroOculto;
    private boolean juegoTerminado;
    private Thread hilo;

    public NumeroOculto() {
        this.numeroOculto = (int) (Math.random() * 101);
        this.juegoTerminado = false;
        
    }
    
    public synchronized int propuestaNumero(int num) {
        if (juegoTerminado) {
            return -1;
        } else if (num == numeroOculto) {
            juegoTerminado = true;
            return 1;
        } else {
            return 0;
        }
    }
    
    
}
