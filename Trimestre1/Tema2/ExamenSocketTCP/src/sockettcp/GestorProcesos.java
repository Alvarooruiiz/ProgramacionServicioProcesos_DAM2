package sockettcp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorProcesos extends Thread {

    private Socket socket;
    private SocketTCPServer server;
    private ObjectOutputStream oos;

    private static Perro[] listaPerros = {
            new Perro("Balto", 98),
            new Perro("Chonino", 40),
            new Perro("Laika", 66),
            new Perro("Stubby", 50),
            new Perro("Smoky", 51),
            new Perro("Barry", 223),
            new Perro("Old Drum", 154)
    };

    public GestorProcesos(Socket socket, SocketTCPServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException ex) {
            Logger.getLogger(GestorProcesos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void realizarProceso() throws IOException {
        try {
            String mensajeRecibido = server.recibirTexto();

            while (!mensajeRecibido.equals("Dame perro")) {
                mensajeRecibido = server.recibirTexto();
            }

            mensajeRecibido = server.recibirTexto();
            System.out.println(mensajeRecibido);

            int tamanioLista = listaPerros.length;

            System.out.println("Enviando tamaño lista");
            server.escribir(tamanioLista);
            System.out.println("Enviando ok Posicion");
            server.escribirTexto("OK. POSICION");

            System.out.println("Recibiendo posicion");
            int posicionRecibida = server.recibirInt();
            System.out.println(posicionRecibida);

            if (posicionRecibida >= tamanioLista || posicionRecibida < 0) {
                server.escribirTexto("ERROR");
            } else {
                server.escribirTexto("ENVIO PERRO");
                server.escribir(listaPerros[posicionRecibida]);
            }
            server.terminar();
        }  catch (IOException e) {
            e.printStackTrace();  // Maneja la excepción según tus necesidades
        }
    }
}