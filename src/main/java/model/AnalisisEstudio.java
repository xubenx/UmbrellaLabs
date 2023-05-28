package model;

public class AnalisisEstudio {
    private int analisis;
    private int estudio;

    public AnalisisEstudio() {}

    @Override
    public String toString() {
        return "AnalisisEstudio{" +
                "analisis=" + analisis +
                ", estudio=" + estudio +
                '}';
    }

    public AnalisisEstudio(int analisis, int estudio) {
        this.analisis = analisis;
        this.estudio = estudio;
    }

    public int getAnalisis() {
        return analisis;
    }

    public void setAnalisis(int analisis) {
        this.analisis = analisis;
    }

    public int getEstudio() {
        return estudio;
    }

    public void setEstudio(int estudio) {
        this.estudio = estudio;
    }
}
