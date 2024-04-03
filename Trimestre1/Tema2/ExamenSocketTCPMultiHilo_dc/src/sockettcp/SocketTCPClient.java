package sockettcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCPClient {

    private String serverIP;
    private int serverPort;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketTCPClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws UnknownHostException, IOException {
        socket = new Socket(serverIP, serverPort);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void stop() throws IOException {
        dos.close();
        dis.close();
        oos.close();
        ois.close();
        socket.close();
    }

    public String leerMensajeTexto() throws IOException {
        String mensaje = dis.readUTF();
        return mensaje;
    }

    public void enviarMensajeTexto(String mensaje) throws IOException {
        dos.writeUTF(mensaje);
    }
    
    public Perro leerPerro() throws IOException, ClassNotFoundException{
        Perro perro = (Perro) ois.readObject();
        return perro;
    }

}
