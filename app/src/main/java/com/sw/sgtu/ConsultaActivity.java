package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sw.sgtu.adapter.QuejaAdapter;
import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.BusLine;
import com.sw.sgtu.modelo.Queja;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaActivity extends AppCompatActivity {

    Intent intent;

    ApiService apiService;
    private final String TAG = ComprarActivity.class.getSimpleName();

    TextView tvToolBar;
    Toolbar myToolbar;

    Spinner spLineaTransporte;

    ArrayList<BusLine> lineasTransporte = new ArrayList<>();
    ArrayList<String> lineasTransporteNombre = new ArrayList<>();
    ArrayAdapter<String> arAdpLineasTransporte = null;

    private RecyclerView recyclerView;
    private List<Queja> listaQuejas = new ArrayList<>();
    private QuejaAdapter quejaAdapter;

    TextView tvSinQuejas;

    int id_linea_transporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Consultar linea de transporte");

        spLineaTransporte = findViewById(R.id.spinnerLineaTransporte);
        getBusLines();

        recyclerView = findViewById(R.id.recycler_view_quejas);
        quejaAdapter = new QuejaAdapter(ConsultaActivity.this, listaQuejas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConsultaActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(quejaAdapter);

        tvSinQuejas = findViewById(R.id.tvSinQuejas);

        spLineaTransporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_linea_transporte = position;
                getListQueja(id_linea_transporte);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getBusLines(){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<BusLine>> call = apiService.getBusLines();
        call.enqueue(new Callback<List<BusLine>>() {
            @Override
            public void onResponse(Call<List<BusLine>> call, Response<List<BusLine>> response) {
                if(response.isSuccessful()){
                    for(BusLine busLine: response.body()){
                        lineasTransporte.add(busLine);
                        lineasTransporteNombre.add(busLine.getNombre());
                    }

                    arAdpLineasTransporte = new ArrayAdapter<String>(ConsultaActivity.this,
                            android.R.layout.simple_dropdown_item_1line, lineasTransporteNombre);

                    spLineaTransporte.setAdapter(arAdpLineasTransporte);
                }else{
                    BusLine b1 = new BusLine();
                    b1.setNombre("Seleccione");
                    BusLine b2 = new BusLine();
                    b2.setNombre("Ate - Callao");
                    lineasTransporteNombre.add(b1.getNombre());
                    lineasTransporteNombre.add(b2.getNombre());
                    arAdpLineasTransporte = new ArrayAdapter<String>(ConsultaActivity.this,
                            android.R.layout.simple_dropdown_item_1line, lineasTransporteNombre);

                    spLineaTransporte.setAdapter(arAdpLineasTransporte);
                }
            }

            @Override
            public void onFailure(Call<List<BusLine>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
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
