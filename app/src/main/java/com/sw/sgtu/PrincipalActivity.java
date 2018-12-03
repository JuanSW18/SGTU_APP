package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    TextView tvToolBar;
    Toolbar myToolbar;

    Intent intent;

    private Bundle bundle;
    int ID_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Inicio");

        bundle = getIntent().getExtras();
        ID_USUARIO = bundle.getInt("ID_USUARIO");
    }

    public void redirectCompra(View view) {
        //intent = new Intent(PrincipalActivity.this, ComprarActivity.class);
        intent = new Intent(PrincipalActivity.this, RegistroCompraActivity.class);
        intent.putExtra("ID_USUARIO", ID_USUARIO);
        startActivity(intent);
    }

    public void redirectReportar(View view) {
        intent = new Intent(PrincipalActivity.this, ReportarActivity.class);
        intent.putExtra("ID_USUARIO", ID_USUARIO);
        startActivity(intent);
    }

    public void redirectMapa(View view) {
        intent = new Intent(PrincipalActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void redirectConsulta(View view) {
        intent = new Intent(PrincipalActivity.this, ConsultaActivity.class);
        intent.putExtra("ID_USUARIO", ID_USUARIO);
        startActivity(intent);
    }
}
