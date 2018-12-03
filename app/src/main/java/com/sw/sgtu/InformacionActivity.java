package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sw.sgtu.conexion.ApiService;

public class InformacionActivity extends AppCompatActivity {

    private Bundle bundle;
    int ID_LINEA_TRANSPORTE;

    Intent intent;

    ApiService apiService;
    private final String TAG = HistorialQuejasActivity.class.getSimpleName();

    TextView tvToolBar;
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvToolBar.setText("Informacion de la Empresa");

        bundle = getIntent().getExtras();
        ID_LINEA_TRANSPORTE = bundle.getInt("ID_LINEA_TRANSPORTE");
    }
}
