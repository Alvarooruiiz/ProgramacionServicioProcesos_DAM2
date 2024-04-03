package sockettcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SocketTCPServer(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("(Servidor) Conexión establecida");
            new GestorProcesos(socket).start();
        }
    }

  

}
