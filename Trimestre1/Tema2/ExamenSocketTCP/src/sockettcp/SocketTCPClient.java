package sockettcp;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketTCPClient {

    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public SocketTCPClient() {
    }

    public void crearConexion() {
        try {
            socket = new Socket("127.0.0.1", 50001);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SocketTCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribir(Object ob) {
        try {

            oos.writeObject(ob);
            oos.flush();
        } catch (IOException ex) {
            ex.getMessage();
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
        socket.close();
        oos.close();
        ois.close();
    }

}
