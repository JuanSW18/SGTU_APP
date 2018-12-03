package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sw.sgtu.adapter.QuejaAdapter;
import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.Queja;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialQuejasActivity extends AppCompatActivity {

    private Bundle bundle;
    //int ID_USUARIO;
    int ID_LINEA_TRANSPORTE;

    Intent intent;

    ApiService apiService;
    private final String TAG = HistorialQuejasActivity.class.getSimpleName();

    TextView tvToolBar;
    Toolbar myToolbar;

    private RecyclerView recyclerView;
    private List<Queja> listaQuejas = new ArrayList<>();
    private QuejaAdapter quejaAdapter;
    TextView tvSinQuejas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_quejas);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Historial de quejas");

        recyclerView = findViewById(R.id.recycler_view_quejas);
        quejaAdapter = new QuejaAdapter(HistorialQuejasActivity.this, listaQuejas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistorialQuejasActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(quejaAdapter);

        tvSinQuejas = findViewById(R.id.tvSinQuejas);

        bundle = getIntent().getExtras();
        //ID_USUARIO = bundle.getInt("ID_USUARIO");
        ID_LINEA_TRANSPORTE = bundle.getInt("ID_LINEA_TRANSPORTE");

        getListQueja(ID_LINEA_TRANSPORTE);
    }

    public void getListQueja(int id_linea_transporte){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<Queja>> call = apiService.getListQueja(id_linea_transporte);
        call.enqueue(new Callback<List<Queja>>() {
            @Override
            public void onResponse(Call<List<Queja>> call, Response<List<Queja>> response) {
                if(response.isSuccessful()){
                    listaQuejas.clear();
                    for (Queja permiso: response.body()){
                        listaQuejas.add(permiso);
                    }
                    quejaAdapter.notifyDataSetChanged();
                    if(listaQuejas.size() == 0){
                        tvSinQuejas.setVisibility(View.VISIBLE);
                    }else{
                        tvSinQuejas.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Queja>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }
}
