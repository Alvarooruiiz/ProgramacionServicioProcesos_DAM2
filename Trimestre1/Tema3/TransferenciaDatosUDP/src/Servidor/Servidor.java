package Servidor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {


        try (BufferedWriter out = new BufferedWriter(new FileWriter("fichero.txt"));DatagramSocket socket = new DatagramSocket(5000)){
            
            

            while (true) {
                byte[] bufferLectura = new byte[1064];

                DatagramPacket datagramaEntrada = new DatagramPacket(bufferLectura, bufferLectura.length);
                socket.receive(datagramaEntrada);

                String mensaje = new String(datagramaEntrada.getData(), 0, datagramaEntrada.getLength());
                if (mensaje.equals("FIN")) {
                    break;
                }
                
                out.write(mensaje+"\n");
            }
            
            System.out.println("Ha finalizado");

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
