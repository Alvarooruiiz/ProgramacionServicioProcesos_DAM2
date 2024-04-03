package lecturaremotatcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
    private static ServerSocket server;
    private static Socket socket;
    private static DataInputStream dis;
    private static DataOutputStream dos;
    
    
    
    public static void main(String[] args) {
        try {
            start();
            String mensajeIN = leerMensaje();
            String mensajeOUT=leerFichero(mensajeIN);
            escribirMensaje(mensajeOUT);
            
            stop();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void start(){
        try {
            server = new ServerSocket(5000);
            socket = server.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static String leerMensaje() throws IOException{
        return dis.readUTF();
    }
    
    
    public static void escribirMensaje(String s) throws IOException{
        dos.writeUTF(s);
    }
    
    public static String leerFichero(String ruta){
        String mensajeOUT="";
        
        try(BufferedReader in = new BufferedReader(new FileReader(ruta))){
                String linea;
                while(((linea=in.readLine())!=null)){
                    mensajeOUT += linea+"\n";
                }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajeOUT;
    }
    
    public static void stop() throws IOException{
        server.close();
        dos.close();
        dis.close();
    }
}
