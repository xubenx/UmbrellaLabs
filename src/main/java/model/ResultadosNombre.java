package model;

public class ResultadosNombre {

    private Integer Orden;

    public Integer getOrden() {
        return Orden;
    }

    @Override
    public String toString() {
        return "ResultadosNombre{" +
                "Orden=" + Orden +
                ", Estudio='" + Estudio + '\'' +
                ", Analisis='" + Analisis + '\'' +
                ", Resultado=" + Resultado +
                '}';
    }

    public void setOrden(Integer orden) {
        Orden = orden;
    }


    public ResultadosNombre() {
    }

    public ResultadosNombre(Integer orden, String estudio, String analisis, Double resultado) {
        Orden = orden;
        Estudio = estudio;
        Analisis = analisis;
        Resultado = resultado;
    }

    public String getEstudio() {
        return Estudio;
    }

    public void setEstudio(String estudio) {
        Estudio = estudio;
    }

    public String getAnalisis() {
        return Analisis;
    }

    public void setAnalisis(String analisis) {
        Analisis = analisis;
    }

    public Double getResultado() {
        return Resultado;
    }

    public void setResultado(Double resultado) {
        Resultado = resultado;
    }

    private  String Estudio;

    private  String Analisis;

    private  Double Resultado;



}
