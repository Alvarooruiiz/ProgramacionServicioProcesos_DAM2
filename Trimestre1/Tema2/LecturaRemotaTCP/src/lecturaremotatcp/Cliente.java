package lecturaremotatcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",5000);
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Introduce el nombre del fichero");
            String fichero = sc.nextLine() + ".txt";
            
            dos.writeUTF(fichero);
                        
            System.out.println(dis.readUTF());
           
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
