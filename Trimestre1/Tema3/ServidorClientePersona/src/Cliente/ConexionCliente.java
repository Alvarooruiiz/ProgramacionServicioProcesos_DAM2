/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class ConexionCliente {
    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public ConexionCliente() {
    }
    
    
    
    public void crearConexion() throws IOException, ClassNotFoundException{
        socket = new Socket("127.0.0.1",5000);
    }
    
    public ArrayList<Persona> leerObjeto() throws IOException, ClassNotFoundException{
        ois = new ObjectInputStream(socket.getInputStream());
        ArrayList<Persona> lista20anos = (ArrayList<Persona>)ois.readObject();
        
        return lista20anos;
    }
    
    public void escribirObjeto(ArrayList<Persona> lista){
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(lista);
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void terminar() throws IOException{
        socket.close();
        oos.close();
        ois.close();
    }
}
