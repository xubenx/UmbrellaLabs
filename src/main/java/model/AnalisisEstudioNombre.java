package model;

public class AnalisisEstudioNombre
{

    private  String Analisis;

    public String getAnalisis() {
        return Analisis;
    }

    public AnalisisEstudioNombre() {
    }

    public AnalisisEstudioNombre(String analisis, String estudio) {
        Analisis = analisis;
        Estudio = estudio;
    }

    public void setAnalisis(String analisis) {
        Analisis = analisis;
    }

    public String getEstudio() {
        return Estudio;
    }

    public void setEstudio(String estudio) {
        Estudio = estudio;
    }

    private String Estudio;


}
