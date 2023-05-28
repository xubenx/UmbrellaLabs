package model;

public class Analisis {
    private int idAnalisis;
    private String nombre;
    private Double limInferior;
    private Double limSuperior;

    public Analisis() {}

    public Analisis(int idAnalisis, String nombre, Double limInferior, Double limSuperior) {
        this.idAnalisis = idAnalisis;
        this.nombre = nombre;
        this.limInferior = limInferior;
        this.limSuperior = limSuperior;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLimInferior() {
        return limInferior;
    }

    public void setLimInferior(Double limInferior) {
        this.limInferior = limInferior;
    }

    public Double getLimSuperior() {
        return limSuperior;
    }

    public void setLimSuperior(Double limSuperior) {
        this.limSuperior = limSuperior;
    }

    @Override
    public String toString() {
        return "Analisis{" +
                "idAnalisis=" + idAnalisis +
                ", nombre='" + nombre + '\'' +
                ", limInferior=" + limInferior +
                ", limSuperior=" + limSuperior +
                '}';
    }
}
