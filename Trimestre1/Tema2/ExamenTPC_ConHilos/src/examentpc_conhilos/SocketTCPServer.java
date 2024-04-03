/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examentpc_conhilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {

    private ServerSocket serverSocket;

    public void iniciarServidor() throws IOException {
        serverSocket = new ServerSocket(50001);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Se ha aceptado");
            GestorProcesos gestorProcesos = new GestorProcesos(socket);
            gestorProcesos.start();
        }
    }
}