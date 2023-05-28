package model;

public class PrecioNombre {
    private  String nombre;

    private  Float precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public PrecioNombre (String nombre, Float precio){
        this.nombre = nombre;
        this.precio = precio;
    }

}
