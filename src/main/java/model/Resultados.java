package model;

public class Resultados {
    private int orden;
    private int estudio;
    private int analisis;
    private Double resultado;

    public Resultados() {}

    public Resultados(int orden, int estudio, int analisis, Double resultado) {
        this.orden = orden;
        this.estudio = estudio;
        this.analisis = analisis;
        this.resultado = resultado;
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

    public int getAnalisis() {
        return analisis;
    }

    public void setAnalisis(int analisis) {
        this.analisis = analisis;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
