/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peluqueria;

/**
 *
 * @author alvar
 */
public class barbero extends Thread {

	private Barberia laBarberia;

	public barbero(Barberia laBarberia) {
		this.laBarberia = laBarberia;
	}

	public void run() {
		while (true) {
			try {
				laBarberia.esperarCliente();
				// Cortar pelo
				Thread.sleep(5000);
				laBarberia.acabarCorte();
				// Decansa un poco
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			;
		}
	}
}