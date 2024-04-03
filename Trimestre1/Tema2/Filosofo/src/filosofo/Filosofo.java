package filosofo;

public class Filosofo extends Thread {
     
    private Mesa mesa;
    private int comensal;
     
    public Filosofo(Mesa m, int comensal){
        this.mesa=m;
        this.comensal=comensal;
        
        
    }
     
    public void run(){
        while(true){
            pensando();
            mesa.cogerTenedores(comensal);
            comiendo();
            mesa.dejarTenedores(comensal);
        }
    }
     
    public void pensando(){
        System.err.println("Filosofo " + comensal + " esta pensando");
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) { }
        
    }
     
    public void comiendo(){
        System.out.println("Filosofo " + comensal + " esta comiendo");
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) { }
    }
}
