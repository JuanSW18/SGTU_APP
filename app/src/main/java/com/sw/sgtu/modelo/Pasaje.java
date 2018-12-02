package com.sw.sgtu.modelo;

public class Pasaje {

    private int id_linea_transporte;
    private int id_usuario;
    private double costo;
    private int paradero_inicial;
    private int paradero_final;

    public Pasaje() {
    }

    public int getLinea_transporte() {
        return id_linea_transporte;
    }

    public void setLinea_transporte(int linea_transporte) {
        this.id_linea_transporte = linea_transporte;
    }

    public int getUsuario() {
        return id_usuario;
    }

    public void setUsuario(int usuario) {
        this.id_usuario = usuario;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getParadero_inicial() {
        return paradero_inicial;
    }

    public void setParadero_inicial(int paradero_inicial) {
        this.paradero_inicial = paradero_inicial;
    }

    public int getParadero_final() {
        return paradero_final;
    }

    public void setParadero_final(int paradero_final) {
        this.paradero_final = paradero_final;
    }


}
