package sockettcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestorProcesos extends Thread {
    
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private static Perro[] listaPerros = {
        new Perro("Balto", 98),
        new Perro("Chonino", 40),
        new Perro("Laika", 66),
        new Perro("Stubby", 50),
        new Perro("Smoky", 51),
        new Perro("Barry", 223),
        new Perro("Old Drum", 154)
    };
    
    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override
    public void run() {
        try {
            realizarProceso();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void realizarProceso() throws IOException {
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        String mensaje;
        do{
        mensaje = dis.readUTF();
        if ("Dame Perro".equalsIgnoreCase(mensaje)) {
            dos.writeUTF(listaPerros.length + "");
            dos.writeUTF("OK. POSICION");

            String mensajePosicion = dis.readUTF();
            int posicion = Integer.parseInt(mensajePosicion)-1;
            if (posicion > listaPerros.length-1) {
                dos.writeUTF("ERROR");
            } else {
                dos.writeUTF("ENVIO PERRO");
                oos.writeObject(listaPerros[posicion]);
            }
        }
        }while(!"Dame Perro".equalsIgnoreCase(mensaje));
    }
}
