package sockettcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {

    private Socket socket;
    private ServerSocket server;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public void iniciarServidor() throws IOException {
        server = new ServerSocket(50001);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            Socket socket = server.accept();
            // Crea una instancia de GestorProcesos para manejar la conexión en un hilo separado
            try (
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
            ) {
                this.ois = ois;
                this.oos = oos;

                GestorProcesos gestorProcesos = new GestorProcesos(socket, this);
                gestorProcesos.start();
            } catch (IOException e) {
                e.printStackTrace();  // Maneja la excepción según tus necesidades
            }
        }
    }
    
    
    public Object recibirObjeto() {
        Object obj = null;
        try {
            obj = ois.readObject();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
        }

        return obj;
    }

    public int recibirInt() {
        int pos = 0;
        try {
            pos = ois.readInt();
        } catch (IOException ex) {
            ex.getMessage();

        }

        return pos;
    }

    public void escribir(Object obj) {
        try {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void escribirTexto(String txt) {
        try {
            oos.writeUTF(txt);
            oos.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public String recibirTexto() {
        String mensajeRecibido = "";
        try {
            mensajeRecibido = ois.readUTF();
        } catch (IOException ex) {
            ex.getMessage();
        }

        return mensajeRecibido;
    }

    public void terminar() throws IOException {
        server.close();
        oos.close();
        ois.close();
    }
}
