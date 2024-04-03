package sockettcp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientePerro {

    public static void main(String[] artgs) throws IOException {
        String mensaje;
        SocketTCPClient cliente = new SocketTCPClient("localhost",50001);
     
        cliente.start();
        
        cliente.enviarMensajeTexto("Dame Gato");
        
        cliente.enviarMensajeTexto("Dame Perro");
        
        mensaje = cliente.leerMensajeTexto();
        System.out.println("El tamaño de la lista de perros es: " + mensaje);
        mensaje = cliente.leerMensajeTexto();
        System.out.println(mensaje);
        int posicion = (int) (Math.random()*9+1);
        System.out.println(posicion);
        cliente.enviarMensajeTexto(posicion + "");
        
        mensaje = cliente.leerMensajeTexto();
        
        if ("ERROR".equalsIgnoreCase(mensaje)) {
            System.out.println("Se ha solicitado una posicion incorrecta dentro de la lista");
        } else{
            try {
                Perro perro = cliente.leerPerro();
                System.out.println(perro.toString());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientePerro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        cliente.stop();
    }
                      
}