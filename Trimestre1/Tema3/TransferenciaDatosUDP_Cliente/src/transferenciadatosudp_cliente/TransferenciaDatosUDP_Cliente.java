package transferenciadatosudp_cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TransferenciaDatosUDP_Cliente {

    public static void main(String[] args) {
        DatagramSocket socketUDP;
        try{
            InetAddress hostServidor = InetAddress.getByName("localhost");
            int puertoServer = 5000;
            String strMensaje;
            
            socketUDP = new DatagramSocket();
            
            
            
            for(int i=0; i<10000; i++){
                
                strMensaje = "Mensaje: " + i;
                byte[] mensaje=strMensaje.getBytes();
                
                DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServer);
                socketUDP.send(peticion);
            }
            
            strMensaje="FIN";
            byte[] mensaje = strMensaje.getBytes();
            DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, hostServidor, puertoServer);
            socketUDP.send(peticion);
            
            
        }catch(IOException e){
            e.getMessage();
        }
    }
    
    
}
