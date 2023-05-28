package model;

public class EstudioOrdenNombre {
    private  int idOrden;
    private String nombreEstudio;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    @Override
    public String toString() {
        return "EstudioOrdenNombre{" +
                "idOrden=" + idOrden +
                ", nombreEstudio='" + nombreEstudio + '\'' +
                ", precio=" + precio +
                '}';
    }

    public EstudioOrdenNombre() {
    }

    public String getNombreEstudio() {
        return nombreEstudio;
    }

    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    private Double precio;

    public EstudioOrdenNombre(int idOrden, String nombreEstudio, Double precio) {
        this.idOrden = idOrden;
        this.nombreEstudio = nombreEstudio;
        this.precio = precio;
    }
}
