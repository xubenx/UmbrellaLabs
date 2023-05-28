package model;

public class Cliente {
    private String RFC;
    private String nombre;
    private String APaterno;
    private String AMaterno;
    private String telefono;
    private String correo;

    public Cliente() {}

    public Cliente(String RFC, String nombre, String APaterno, String AMaterno, String telefono, String correo) {
        this.RFC = RFC;
        this.nombre = nombre;
        this.APaterno = APaterno;
        this.AMaterno = AMaterno;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAPaterno() {
        return APaterno;
    }

    public void setAPaterno(String APaterno) {
        this.APaterno = APaterno;
    }

    public String getAMaterno() {
        return AMaterno;
    }

    public void setAMaterno(String AMaterno) {
        this.AMaterno = AMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "RFC='" + RFC + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", APaterno='" + APaterno + '\'' +
                ", AMaterno='" + AMaterno + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Correo='" + correo + '\'' +
                '}';
    }

}
