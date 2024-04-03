package sockettcp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientePerro {

    public static void main(String[] artgs) {

        try {
            SocketTCPClient cliente = new SocketTCPClient();
            cliente.crearConexion();
            String mensaje;

            System.out.println("Enviando Dame gato");
            cliente.escribirTexto("Dame gato");
            System.out.println("Enviando Dame perro");
            cliente.escribirTexto("perro");

            System.out.println("Esperando a recibir tamaño lista");
            Integer tamanioLista = (Integer) cliente.recibirObjeto();

            if (tamanioLista != null) {
                System.out.println(tamanioLista.intValue());

                System.out.println("Esperando a recibir ok Posicion");
                mensaje = cliente.recibirTexto();
                System.out.println(mensaje);

                int posAleatoria = (int) (Math.random() * 10);
                System.out.println("Enviando posicion aleatoria");
                cliente.escribir(posAleatoria);

                mensaje = cliente.recibirTexto();

                if (mensaje.equals("ERROR")) {
                    System.out.println("Ha indicado una posicion incorrecta");
                } else {
                    System.out.println("Se va a recibir un perro");
                    Perro p = (Perro) cliente.recibirObjeto();
                    System.out.println(p);
                }
            } else {
                System.out.println("El servidor ha cerrado la conexión antes de recibir el tamaño de la lista.");
            }

            cliente.terminar();
        } catch (IOException ex) {
            Logger.getLogger(ClientePerro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
