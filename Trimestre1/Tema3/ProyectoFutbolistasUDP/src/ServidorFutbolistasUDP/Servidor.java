package ServidorFutbolistasUDP;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor = new ServerSocket();
        servidor.ejecutarServidor();
    }
}