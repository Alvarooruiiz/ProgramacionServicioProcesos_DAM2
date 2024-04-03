/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examentpc_conhilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestorProcesos extends Thread {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private static Perro[] listaPerros = {
        new Perro("Balto", 98),
        new Perro("Chonino", 40),
        new Perro("Laika", 66),
        new Perro("Stubby", 50),
        new Perro("Smoky", 51),
        new Perro("Barry", 223),
        new Perro("Old Drum", 154)
    };

    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void realizarProceso() throws IOException {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            int tamanioLista = listaPerros.length;
            oos.writeInt(tamanioLista);
            oos.flush();

            System.out.println("Esperando mensaje del cliente...");

            String mensajeRecibido;
            do {
                mensajeRecibido = ois.readUTF();
                System.out.println("Mensaje recibido: " + mensajeRecibido);

                if ("Dame Perro".equalsIgnoreCase(mensajeRecibido)) {
                    System.out.println("Enviando OK. Posicion");
                    oos.writeUTF("OK. POSICION");
                    oos.flush();

                    System.out.println("Recibiendo posicion");
                    int posicion = ois.readInt() - 1;
                    if (posicion >= 0 && posicion < listaPerros.length) {
                        oos.writeObject(listaPerros[posicion]);
                        oos.flush();
                    } else {
                        oos.writeObject(null);
                        oos.flush();
                    }
                }
            } while (!"Dame Perro".equalsIgnoreCase(mensajeRecibido));

            socket.close();
            ois.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
