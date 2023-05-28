package model;

public class EstudioOrden {
    private int orden;
    private int estudio;
    private double precio;

    public EstudioOrden() {}

    public EstudioOrden(int orden, int estudio, double precio) {
        this.orden = orden;
        this.estudio = estudio;
        this.precio = precio;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getEstudio() {
        return estudio;
    }

    public void setEstudio(int estudio) {
        this.estudio = estudio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
