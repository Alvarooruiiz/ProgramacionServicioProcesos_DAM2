/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peluqueria;

public class laBarberia {

	public static void main(String[] args) {
		final int nSillas = 4;
		final int nClientes = 10;
		
		Barberia laBarberia = new Barberia(nSillas);
		barbero elBarbero = new barbero(laBarberia);
		cliente[] losClientes = new cliente[10];

		elBarbero.start();

		for (int i = 0; i < nClientes; i++) {
			losClientes[i] = new cliente(laBarberia, i);
			losClientes[i].start();
		}
	}
}