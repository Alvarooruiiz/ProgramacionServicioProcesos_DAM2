package filosofo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesa {
     
    private boolean[] tenedores;
    private static int i;
     
    public Mesa(int numTenedores){
        tenedores = new boolean[numTenedores];
    }
     
    public int tenedorIzquierda(int i){
        return i;
    }
     
    public int tenedorDerecha(int i){
        int num=0;
        for(int x=0; x<tenedores.length; x++){
            if(i==tenedores.length-1){
                num=0;
            }else num=i+1;
        }
        return num;
    }
     
    public synchronized void cogerTenedores(int comensal){
        while((tenedores[tenedorIzquierda(comensal)]==true) || (tenedores[tenedorDerecha(comensal)])){
            try{
                wait();    
            } catch (InterruptedException ex) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tenedores[tenedorIzquierda(comensal)]=true;
        tenedores[tenedorDerecha(comensal)]=true;
    }
     
    public synchronized void dejarTenedores (int comensal){
        tenedores[tenedorDerecha(comensal)]=false;
        tenedores[tenedorDerecha(comensal)]=false;
        notifyAll();
        
    }

}
