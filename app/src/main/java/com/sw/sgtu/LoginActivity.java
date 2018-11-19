package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void redirectRegistro(View view) {
        intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view) {
        intent = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }
}
