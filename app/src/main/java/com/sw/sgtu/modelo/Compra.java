package com.sw.sgtu.modelo;

public class Compra {

    private String linea_transporte;
    private String fecha;
    private double costo;
    private String paradero_inicial;
    private String paradero_final;

    public String getLinea_transporte() {
        return linea_transporte;
    }

    public void setLinea_transporte(String linea_transporte) {
        this.linea_transporte = linea_transporte;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getParadero_inicial() {
        return paradero_inicial;
    }

    public void setParadero_inicial(String paradero_inicial) {
        this.paradero_inicial = paradero_inicial;
    }

    public String getParadero_final() {
        return paradero_final;
    }

    public void setParadero_final(String paradero_final) {
        this.paradero_final = paradero_final;
    }
}
