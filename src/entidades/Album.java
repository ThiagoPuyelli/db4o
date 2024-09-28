package entidades;

import java.util.ArrayList;

public class Album {
    private String nombre;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

    public Album () {
        this(null);
    }
    public Album (String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
}
