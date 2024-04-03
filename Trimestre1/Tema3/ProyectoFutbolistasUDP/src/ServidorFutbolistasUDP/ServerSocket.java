package ServidorFutbolistasUDP;

import Futbolistas.Futbolista;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerSocket {
    
    private DatagramSocket socket;
    private DatagramPacket datagramaEntrada;

    public void ejecutarServidor() {
        try {
            System.out.println("(Servidor) Creando socket...");
            crearSocket();

            System.out.println("(Servidor) Recibiendo ArrayList de futbolistas...");
            ArrayList<Futbolista> listaFutbolistas = recibirFutbolistas();

            System.out.println("(Servidor) Lista de futbolistas recibida.");
            System.out.println(listaFutbolistas);

            System.out.println("(Servidor) Introduzca el número de dorsal del jugador que desea enviar: ");
            Scanner scanner = new Scanner(System.in);
            int dorsal = scanner.nextInt();

            Futbolista jugadorAEnviar = obtenerJugadorPorDorsal(listaFutbolistas, dorsal);

            System.out.println("(Servidor) Enviando nombre del futbolista...");
            enviarNombreJugador(jugadorAEnviar);

            cerrarSockets();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void crearSocket() throws SocketException {
        socket = new DatagramSocket(49171);
    }

    private ArrayList<Futbolista> recibirFutbolistas() throws IOException, ClassNotFoundException {
        byte[] bufferLectura = new byte[1024];
        datagramaEntrada = new DatagramPacket(bufferLectura, bufferLectura.length);
        socket.receive(datagramaEntrada);

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(bufferLectura));
        return (ArrayList<Futbolista>) objectInputStream.readObject();
    }

    private Futbolista obtenerJugadorPorDorsal(ArrayList<Futbolista> listaFutbolistas, int dorsal) {
        Futbolista jugadorAEnviar = null;
        for (Futbolista futbolista : listaFutbolistas) {
            if (futbolista.getDorsal() == dorsal) {
                jugadorAEnviar = futbolista;
                break;
            }
        }
        return jugadorAEnviar;
    }

    private void enviarNombreJugador(Futbolista jugador) throws IOException {
        String mensajeEnviado;
        if (jugador != null) {
            mensajeEnviado = jugador.getNombre();
        } else {
            mensajeEnviado = "No se encontró un futbolista con el dorsal especificado.";
        }

        byte[] bytesMensaje = mensajeEnviado.getBytes();

        DatagramPacket datagramaSalida = new DatagramPacket(bytesMensaje, bytesMensaje.length,
                datagramaEntrada.getAddress(), datagramaEntrada.getPort());

        socket.send(datagramaSalida);
    }

    private void cerrarSockets() {
        System.out.println("(Servidor) Cerrado sockets...");
        socket.close();
        System.out.println("(Servidor) Socket cerrado.");
    }
    
}
