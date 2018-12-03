package com.sw.sgtu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.BusLine;
import com.sw.sgtu.modelo.BusStop;
import com.sw.sgtu.modelo.Pasaje;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComprarActivity extends AppCompatActivity {

    Intent intent;

    ApiService apiService;
    private final String TAG = ComprarActivity.class.getSimpleName();

    TextView tvToolBar;
    Toolbar myToolbar;

    Spinner spLineaTransporte;
    Spinner spParaderoInicial;
    Spinner spParaderoFinal;

    ArrayList<BusLine> lineasTransporte = new ArrayList<>();
    ArrayList<String> lineasTransporteNombre = new ArrayList<>();
    ArrayAdapter<String> arAdpLineasTransporte = null;

    ArrayList<BusStop> busStops = new ArrayList<>();
    ArrayList<String> paradero_nombre= new ArrayList<>();
    ArrayAdapter<String> arAdpParaderos = null;

    TextView tvPrecio;
    int pasaje_paradero_inicio=0;
    int pasaje_paradero_fin=0;

    Toast toast;

    private Bundle bundle;
    int ID_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        spLineaTransporte = findViewById(R.id.spinnerLineaTransporte);
        spParaderoInicial = findViewById(R.id.spinnerParaderoInicial);
        spParaderoFinal = findViewById(R.id.spinnerParaderoFinal);

        tvPrecio = findViewById(R.id.tvPrecio);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Comprar pasaje");

        bundle = getIntent().getExtras();
        ID_USUARIO = bundle.getInt("ID_USUARIO");

        getBusLines();

        spLineaTransporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    getParaderoByIdRoute(1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spParaderoInicial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pasaje_paradero_inicio = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spParaderoFinal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pasaje_paradero_fin = position;
                tvPrecio.setText("S/. 1.00");
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

                    arAdpLineasTransporte = new ArrayAdapter<String>(ComprarActivity.this,
                            android.R.layout.simple_dropdown_item_1line, lineasTransporteNombre);

                    spLineaTransporte.setAdapter(arAdpLineasTransporte);
                }else{
                    BusLine b1 = new BusLine();
                    b1.setNombre("Seleccione");
                    BusLine b2 = new BusLine();
                    b2.setNombre("Ate - Callao");
                    lineasTransporteNombre.add(b1.getNombre());
                    lineasTransporteNombre.add(b2.getNombre());
                    arAdpLineasTransporte = new ArrayAdapter<String>(ComprarActivity.this,
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

    public void getParaderoByIdRoute(int id_ruta){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<List<BusStop>> call = apiService.getParaderoByIdRoute(id_ruta);
        call.enqueue(new Callback<List<BusStop>>() {
            @Override
            public void onResponse(Call<List<BusStop>> call, Response<List<BusStop>> response) {
                if(response.isSuccessful()){
                    for(BusStop busStop : response.body()){
                            busStops.add(busStop);
                            paradero_nombre.add(busStop.getNombre());
                    }

                    arAdpParaderos = new ArrayAdapter<String>(ComprarActivity.this,
                        android.R.layout.simple_dropdown_item_1line, paradero_nombre);

                    spParaderoInicial.setAdapter(arAdpParaderos);
                    spParaderoFinal.setAdapter(arAdpParaderos);

                }else {
                    //Paraderos por si no responde
                    BusStop p1 = new BusStop();
                    p1.setNombre("Seleccione");
                    BusStop p2 = new BusStop();
                    p2.setNombre("Av. Colonial");
                    BusStop p3 = new BusStop();
                    p3.setNombre("Av. Venzuela");

                    paradero_nombre.add(p1.getNombre());
                    paradero_nombre.add(p2.getNombre());
                    paradero_nombre.add(p3.getNombre());

                    arAdpParaderos = new ArrayAdapter<String>(ComprarActivity.this,
                            android.R.layout.simple_dropdown_item_1line, paradero_nombre);

                    spParaderoInicial.setAdapter(arAdpParaderos);
                    spParaderoFinal.setAdapter(arAdpParaderos);
                }
            }

            @Override
            public void onFailure(Call<List<BusStop>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }

    public void confirmarCompra(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmacion de compra");
        builder.setMessage("¿Desea realizar la compra?");
        builder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Pasaje pasaje = new Pasaje();
                        pasaje.setLinea_transporte(1);
                        pasaje.setUsuario(ID_USUARIO);
                        pasaje.setCosto(1.5);
                        pasaje.setParadero_inicial(pasaje_paradero_inicio);
                        pasaje.setParadero_final(pasaje_paradero_fin);
                        comprarPasaje(pasaje);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(ComprarActivity.this, PrincipalActivity.class);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void comprarPasaje(Pasaje request){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<Pasaje> call = apiService.comprarPasaje(request);
        call.enqueue(new Callback<Pasaje>() {
            @Override
            public void onResponse(Call<Pasaje> call, Response<Pasaje> response) {
                if(response.isSuccessful()){
                     toast = Toast.makeText(ComprarActivity.this, "Compra realizada", Toast.LENGTH_SHORT);
                     toast.show();

                     intent = new Intent(ComprarActivity.this, RegistroCompraActivity.class);
                     intent.putExtra("ID_USUARIO", ID_USUARIO);
                     startActivity(intent);

                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pasaje> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }
}
