package filosofo;

public class Principal {
     
    public static void main(String[] args) {
        Mesa m = new Mesa(5);
        for (int i = 0; i <= 4; i++) {
            Filosofo f = new Filosofo(m, i);
            f.start();
        }
    }
}
