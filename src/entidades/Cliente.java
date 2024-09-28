
package entidades;


public class Cliente
{
    // instance variables - replace the example below with your own
    private int id;
    private String descripcion;


    public Cliente(int id, String descripcion)
    {
        // initialise instance variables
       this.id=id;
       this.descripcion=descripcion;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cliente " +'\''+
                "id=" + id +'\''+
                "descripcion='" + descripcion + '\'';
    }
}
