package filosofosconsemaforos;

public class Filosofo extends Thread {
    private Mesa mesa;
    private int comensal;

    public Filosofo(Mesa m, int comensal) {
        this.mesa = m;
        this.comensal = comensal;
    }

    public void run() {
        while (true) {
            try {
                mesa.cogerTenedores(comensal);
                comiendo();
                mesa.dejarTenedores(comensal);
                pensando();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void pensando() throws InterruptedException {
        System.out.println("Filosofo " + comensal + " está pensando");
        Thread.sleep((long) (Math.random() * 4000));
    }

    public void comiendo() throws InterruptedException {
        System.out.println("Filosofo " + comensal + " está comiendo");
        Thread.sleep((long) (Math.random() * 4000));
    }
}

