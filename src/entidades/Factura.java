package entidades;

public class Factura {

    private int nro;
    private int id;
    private Double importe;

    public Factura(int nrof, int idc, Double importe) {
        this.nro = nrof;
        this.id = idc;
        this.importe = importe;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "nro=" + nro +
                ", id=" + id +
                ", importe=" + importe +
                '}';
    }
}
