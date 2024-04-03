package Futbolistas;

import java.io.Serializable;

public class Futbolista implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int dorsal;
    private String posicion;

    public Futbolista(String nombre, int dorsal, String posicion) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    @Override
    public String toString() {
        return "Futbolista{" + "nombre=" + nombre + ", dorsal=" + dorsal + ", posicion=" + posicion + '}';
    }
    
    
}
