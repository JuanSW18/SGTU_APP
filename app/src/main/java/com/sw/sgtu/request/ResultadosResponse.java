package com.sw.sgtu.request;

import com.google.gson.annotations.SerializedName;

public class ResultadosResponse {

    @SerializedName("linea_transporte")
    private String linea_transporte;

    public String getLinea_transporte() {
        return linea_transporte;
    }

    public void setLinea_transporte(String linea_transporte) {
        this.linea_transporte = linea_transporte;
    }
}
