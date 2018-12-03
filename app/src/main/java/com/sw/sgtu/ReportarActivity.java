package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.BusLine;
import com.sw.sgtu.modelo.Queja;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportarActivity extends AppCompatActivity {

    Intent intent;

    ApiService apiService;
    private final String TAG = ComprarActivity.class.getSimpleName();

    Toast toast;

    TextView tvToolBar;
    Toolbar myToolbar;

    EditText edDescripcion;

    Spinner spLineaTransporte;

    //ArrayList<BusLine> lineasTransporte = new ArrayList<>();
    ArrayList<String> lineasTransporteNombre = new ArrayList<>();
    ArrayAdapter<String> arAdpLineasTransporte = null;

    int id_linea_transporte;

    Queja queja;
    private RecyclerView recyclerView;

    private Bundle bundle;
    int ID_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        spLineaTransporte = findViewById(R.id.spinnerLineaTransporte);
        edDescripcion = findViewById(R.id.edDescripcion);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Reportar");

        bundle = getIntent().getExtras();
        ID_USUARIO = bundle.getInt("ID_USUARIO");

        getBusLines();

        spLineaTransporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_linea_transporte = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void reportar(View view) {
        if(edDescripcion.getText().toString().isEmpty()){
            toast = Toast.makeText(ReportarActivity.this, "Descripcion vacia!", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            if(id_linea_transporte == 0){
                toast = Toast.makeText(ReportarActivity.this,
                        "Seleccione una linea de transporte!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }else {
                queja = new Queja();
                queja.setId_usuario(ID_USUARIO);
                queja.setId_linea_transporte(id_linea_transporte);
                queja.setDescripcion(edDescripcion.getText().toString());
                enviarQueja(queja);
            }
        }
    }

    public void enviarQueja(Queja request){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<Queja> call = apiService.enviarQueja(request);
        call.enqueue(new Callback<Queja>() {
            @Override
            public void onResponse(Call<Queja> call, Response<Queja> response) {
                if(response.isSuccessful()){
                    toast = Toast.makeText(ReportarActivity.this, "Reporte enviado", Toast.LENGTH_SHORT);
                    toast.show();
                    intent = new Intent(ReportarActivity.this, PrincipalActivity.class);
                    intent.putExtra("ID_USUARIO", ID_USUARIO);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Queja> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
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
                    BusLine busLineFake = new BusLine();
                    busLineFake.setNombre("Seleccione");
                    lineasTransporteNombre.add(busLineFake.getNombre());

                    for(BusLine busLine: response.body()){
                        //lineasTransporte.add(busLine);
                        lineasTransporteNombre.add(busLine.getNombre());
                    }

                    arAdpLineasTransporte = new ArrayAdapter<String>(ReportarActivity.this,
                            android.R.layout.simple_dropdown_item_1line, lineasTransporteNombre);

                    spLineaTransporte.setAdapter(arAdpLineasTransporte);
                }else{
                    BusLine b1 = new BusLine();
                    b1.setNombre("Seleccione");
                    BusLine b2 = new BusLine();
                    b2.setNombre("Ate - Callao");
                    /*lineasTransporteNombre.add(b1.getNombre());
                    lineasTransporteNombre.add(b2.getNombre());
                    arAdpLineasTransporte = new ArrayAdapter<String>(ReportarActivity.this,
                            android.R.layout.simple_dropdown_item_1line, lineasTransporteNombre);

                    spLineaTransporte.setAdapter(arAdpLineasTransporte);*/
                }
            }

            @Override
            public void onFailure(Call<List<BusLine>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }
}
