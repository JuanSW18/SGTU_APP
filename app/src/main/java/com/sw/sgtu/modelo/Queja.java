package com.sw.sgtu.modelo;

public class Queja {

    private int linea_transporte;
    private int usuario;
    private String descripcion;

    public Queja() {
    }

    public Queja(int id_linea_transporte, int id_usuario, String descripcion) {
        this.linea_transporte = id_linea_transporte;
        this.usuario = id_usuario;
        this.descripcion = descripcion;
    }

    public int getId_linea_transporte() {
        return linea_transporte;
    }

    public void setId_linea_transporte(int id_linea_transporte) {
        this.linea_transporte = id_linea_transporte;
    }

    public int getId_usuario() {
        return usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.usuario = id_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
