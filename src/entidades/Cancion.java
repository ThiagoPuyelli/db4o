package entidades;

public class Cancion {
    private String nombre;
    private String album;

    public Cancion (String nombre, String album) {
        this.nombre = nombre;
        this.album = album;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
