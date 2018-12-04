package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.sw.sgtu.adapter.ResultadosAdapter;
import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.ParaderoInicioFin;
import com.sw.sgtu.request.ListResultados;
import com.sw.sgtu.request.ResultadosResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosActivity extends AppCompatActivity {

    TextView tvToolBar;
    Toolbar myToolbar;

    Intent intent;

    private RecyclerView recyclerView;
    private List<ResultadosResponse> listaResultados = new ArrayList<>();
    private ResultadosAdapter resultadosAdapter;

    ApiService apiService;
    private final String TAG = ComprarActivity.class.getSimpleName();

    Bundle bundle;

    int ID_PARADERO_INICIAL;
    int ID_PARADERO_FINAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Resultados");

        recyclerView = findViewById(R.id.recycler_view_paraderos_posibles);
        resultadosAdapter = new ResultadosAdapter(ResultadosActivity.this, listaResultados);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ResultadosActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(resultadosAdapter);

        bundle = getIntent().getExtras();
        ID_PARADERO_INICIAL = bundle.getInt("ID_PARADERO_INICIAL");
        ID_PARADERO_FINAL = bundle.getInt("ID_PARADERO_FINAL");

        System.out.println("ID_PARADERO_INICIAL: " + ID_PARADERO_INICIAL);
        System.out.println("ID_PARADERO_FINAL: " + ID_PARADERO_FINAL);

        ParaderoInicioFin paraderoInicioFin = new ParaderoInicioFin();
        paraderoInicioFin.setId_paradero_inicial(ID_PARADERO_INICIAL);
        paraderoInicioFin.setId_paredero_final(ID_PARADERO_FINAL);
        getPosiblesLineasTransporte(paraderoInicioFin);
    }

    public void getPosiblesLineasTransporte(ParaderoInicioFin paraderoInicioFin){
        apiService = ApiAdapter.createServiceSecondAPI(ApiService.class);
        Call<ListResultados> call = apiService.getPosiblesLineasTransporte(paraderoInicioFin);
        call.enqueue(new Callback<ListResultados>() {
            @Override
            public void onResponse(Call<ListResultados> call, Response<ListResultados> response) {
                if(response.isSuccessful()){
                    List<ResultadosResponse> resultadosResponse = response.body().getResultados();
                    for(ResultadosResponse r: resultadosResponse){
                        if(!r.getLinea_transporte().contains("No existe"))
                            listaResultados.add(r);
                    }
                    resultadosAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ListResultados> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }
}
