package com.sw.sgtu.modelo;

import com.google.gson.annotations.SerializedName;

public class BusStop {

    @SerializedName("id_bus_stop")
    private int id_paradero;

    @SerializedName("name")
    private String nombre;

    @SerializedName("address")
    private String direccion;

    public int getId_paradero() {
        return id_paradero;
    }

    public void setId_paradero(int id_paradero) {
        this.id_paradero = id_paradero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
