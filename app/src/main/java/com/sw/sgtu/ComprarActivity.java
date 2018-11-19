package com.sw.sgtu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ComprarActivity extends AppCompatActivity {

    TextView tvToolBar;
    Toolbar myToolbar;

    Spinner spLineaTransporte;
    Spinner spParaderoInicial;
    Spinner spParaderoFinal;

    ArrayList<String> lineasTransporte = new ArrayList<>();
    ArrayAdapter<String> arAdpLineasTransporte = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        spLineaTransporte = findViewById(R.id.spinnerLineaTransporte);
        spParaderoInicial = findViewById(R.id.spinnerParaderoInicial);
        spParaderoFinal = findViewById(R.id.spinnerParaderoFinal);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Comprar pasaje");

        lineasTransporte.add("Seleccione");
        lineasTransporte.add("Ate - Callao");
        lineasTransporte.add("Puente Piedra - Santa Anita");

        arAdpLineasTransporte = new ArrayAdapter<String>(ComprarActivity.this,
                android.R.layout.simple_dropdown_item_1line, lineasTransporte);

        spLineaTransporte.setAdapter(arAdpLineasTransporte);
        spParaderoInicial.setAdapter(arAdpLineasTransporte);
        spParaderoFinal.setAdapter(arAdpLineasTransporte);
    }
}
