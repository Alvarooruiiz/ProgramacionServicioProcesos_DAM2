/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Cliente.Persona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class ConexionServidor {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public ConexionServidor() {
    }
    
    
    
    public void crearConexion(){
        try {
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public ArrayList<Persona> recibirObjeto(){
        ArrayList<Persona> lista = new ArrayList<>();
        
        try {
            
            lista = (ArrayList<Persona>) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    public void enviarObjeto(ArrayList<Persona> lista){
        try {
            
            oos.writeObject(lista);
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void terminar() throws IOException{
        serverSocket.close();
        oos.close();
        ois.close();
    }
}
