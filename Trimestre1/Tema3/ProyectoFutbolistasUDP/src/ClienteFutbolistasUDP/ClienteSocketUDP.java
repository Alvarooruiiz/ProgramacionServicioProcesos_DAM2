/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteFutbolistasUDP;

import Futbolistas.Futbolista;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ClienteSocketUDP {
    
    private DatagramSocket socketUDP;
    private ArrayList listaFutbolistas;
    
    public void ejecutarCliente() {
        listaFutbolistas = new ArrayList<>();
        listaFutbolistas.add(new Futbolista("Messi", 10, "Delantero"));
        listaFutbolistas.add(new Futbolista("Ronaldo", 7, "Delantero"));
        listaFutbolistas.add(new Futbolista("Benzema", 9, "Delantero"));
        listaFutbolistas.add(new Futbolista("Sergio Ramos", 4, "Defensa"));
        listaFutbolistas.add(new Futbolista("Iker Casillas", 1, "Portero"));
    
        try {
            System.out.println("(Cliente): Estableciendo parámetros de conexión...");
            InetAddress hostServidor = InetAddress.getByName("localhost");
            int puertoServidor = 49171;

            crearSocket();
            enviarFutbolistas(hostServidor, puertoServidor);

            recibirNombreJugador(hostServidor, puertoServidor);

            cerrarSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    private void crearSocket() throws SocketException {
        System.out.println("(Cliente): Creando socket...");
        socketUDP = new DatagramSocket();
    }
    
    private void enviarFutbolistas(InetAddress host, int puerto) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(listaFutbolistas);

        byte[] mensaje = byteArrayOutputStream.toByteArray();
        DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, host, puerto);
        socketUDP.send(peticion);
    }
    
    private void recibirNombreJugador(InetAddress host, int puerto) throws IOException {
        System.out.println("(Cliente) Recibiendo nombre del futbolista....");
        byte[] buffer = new byte[1024];
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, host, puerto);
        socketUDP.receive(respuesta);
        System.out.println("(Cliente): Mensaje recibido: " + new String(buffer));
    }
    
    private void cerrarSocket() {
        System.out.println("(Cliente): Cerrando socket...");
        socketUDP.close();
        System.out.println("(Cliente) Socket cerrado.");
    }
}
