package model;

public class Estudio {
    private int idEstudio;
    private String nombre;
    private Double precio;

    public Estudio() {}

    public Estudio(int idEstudio, String nombre, Double precio) {
        this.idEstudio = idEstudio;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
