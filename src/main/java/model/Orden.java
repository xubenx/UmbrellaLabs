package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Orden {
    private int idOrden;
    private String cliente;
    private Date fecha;
    private double subtotal;
    private double iva;
    private double total;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Orden() {}

    public Orden(int idOrden, String cliente, String fecha, double subtotal, double iva, double total) throws ParseException {
        this.idOrden = idOrden;
        this.cliente = cliente;
        this.fecha = dateFormat.parse(fecha);
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return dateFormat.format(fecha);
    }

    public void setFecha(String fecha) throws ParseException {
        this.fecha = dateFormat.parse(fecha);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
