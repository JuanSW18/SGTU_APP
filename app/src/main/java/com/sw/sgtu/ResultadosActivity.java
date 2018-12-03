package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sw.sgtu.adapter.ResultadosAdapter;
import com.sw.sgtu.request.ResultadosResponse;

import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {

    TextView tvToolBar;
    Toolbar myToolbar;

    Intent intent;

    private RecyclerView recyclerView;
    private List<ResultadosResponse> listaResultados = new ArrayList<>();
    private ResultadosAdapter resultadosAdapter;

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
    }
}
