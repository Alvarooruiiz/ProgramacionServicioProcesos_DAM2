package examentpc_conhilos;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketTPCCliente {

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketTPCCliente() {
    }

    public void crearConexion() {
        try {
            socket = new Socket("127.0.0.1", 50001);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SocketTPCCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribir(Object ob) {
        try {
            oos.writeObject(ob);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object recibirObjeto() {
        Object obj = null;

        try {
            obj = ois.readObject();
        } catch (EOFException eof) {
            System.err.println("El servidor ha cerrado la conexión.");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return obj;
    }

    public void escribirTexto(String txt) {
        try {
            oos.writeUTF(txt);
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String recibirTexto() {
        String mensajeRecibido = "";
        try {
            mensajeRecibido = ois.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return mensajeRecibido;
    }

    public int recibirEntero() {
        int entero = -1;
        try {
            entero = ois.readInt();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entero;
    }

    public void terminar() throws IOException {
        socket.close();
        oos.close();
        ois.close();
    }

    public void esperarContinuar() {
        try {
            ois.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
