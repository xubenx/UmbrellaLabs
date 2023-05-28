package model;

public class ResultadosReporte
{

    private String Nombre;
    private Double limInferior;

    @Override
    public String toString() {
        return "ResultadosReporte{" +
                "Nombre='" + Nombre + '\'' +
                ", limInferior=" + limInferior +
                ", limSuperior=" + limSuperior +
                ", Resultado=" + Resultado +
                '}';
    }

    private Double limSuperior;

    public ResultadosReporte() {
    }

    public ResultadosReporte(String Nombre, Double limInferior, Double limSuperior, Double resultado) {
        this.Nombre = Nombre;
        this.limInferior = limInferior;
        this.limSuperior = limSuperior;
        Resultado = resultado;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
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

    public Double getResultado() {
        return Resultado;
    }

    public void setResultado(Double resultado) {
        Resultado = resultado;
    }

    private  Double Resultado;
}
