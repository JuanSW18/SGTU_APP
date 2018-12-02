package com.sw.sgtu.modelo;

public class BusLine {


    private int id_bus_line;
    private String  nombre;
    private String empresa;

    public int getId_bus_line() {
        return id_bus_line;
    }

    public void setId_bus_line(int id_bus_line) {
        this.id_bus_line = id_bus_line;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
