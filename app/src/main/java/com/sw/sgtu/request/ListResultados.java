package com.sw.sgtu.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResultados {

    @SerializedName("resultados")
    private List<ResultadosResponse> resultados = null;

    public List<ResultadosResponse> getResultados() {
        return resultados;
    }

    public void setResultados(List<ResultadosResponse> resultados) {
        this.resultados = resultados;
    }
}
