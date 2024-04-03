package examentpc_conhilos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientePerro {

    public static void main(String[] args) {

        try {
            SocketTPCCliente cliente = new SocketTPCCliente();
            cliente.crearConexion();

            System.out.println("Enviando Dame perro");
            cliente.escribirTexto("Dame Perro");

            System.out.println("Esperando a recibir tamaño lista");
            int tamanioLista = cliente.recibirEntero();
            System.out.println(tamanioLista);

            System.out.println("Esperando a recibir OK Posicion");
            String mensaje = cliente.recibirTexto();
            System.out.println(mensaje);

            int posAleatoria = (int) (Math.random() * tamanioLista);
            System.out.println("Enviando posicion aleatoria");
            cliente.escribir(posAleatoria);

            mensaje = cliente.recibirTexto();

            if (mensaje.equals("ERROR")) {
                System.out.println("Ha indicado una posición incorrecta");
            } else {
                System.out.println("Se va a recibir un perro");
                Perro p = (Perro) cliente.recibirObjeto();
                if (p != null) {
                    System.out.println("Se va a recibir un perro");
                    System.out.println(p);
                } else {
                    System.out.println("Error al recibir el perro");
                }
            }

            cliente.esperarContinuar();

            cliente.terminar();
        } catch (IOException ex) {
            Logger.getLogger(ClientePerro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
