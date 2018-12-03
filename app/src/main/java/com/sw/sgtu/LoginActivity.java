package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.Usuario;
import com.sw.sgtu.request.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Intent intent;

    ApiService apiService;
    private final String TAG = RegistroActivity.class.getSimpleName();

    Toast toast;

    Usuario usuario;
    LoginResponse loginRequest;

    EditText edUsuario;
    EditText edPassword;

    int ID_USUARIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsuario = findViewById(R.id.edUser);
        edPassword = findViewById(R.id.edPassword);
    }

    public void redirectRegistro(View view) {
        intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view) {
        if(edUsuario.getText().toString().isEmpty() ||
                edPassword.getText().toString().isEmpty()){
            toast = Toast.makeText(LoginActivity.this, "Faltan datos", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            usuario = new Usuario();
            usuario.setUsuario(edUsuario.getText().toString());
            usuario.setPassword(edPassword.getText().toString());
            validarUsuario(usuario);
            /*userRequest = new LoginResponse();
            userRequest.setUsuario(edUsuario.getText().toString());
            userRequest.setPassword(edPassword.getText().toString());
            validarUsuario(userRequest);*/
        }

    }

    public void validarUsuario(Usuario request){
        apiService = ApiAdapter.createServiceSecondAPI(ApiService.class);
        Call<LoginResponse> call = apiService.validarUsuario(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    int valido = response.body().getValid();
                    if(valido == 1){
                        ID_USUARIO = response.body().getUserResponse().get(0).getId_usuario();

                        toast = Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT);
                        toast.show();
                        intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                        intent.putExtra("ID_USUARIO", ID_USUARIO);
                        startActivity(intent);
                    }else{
                        toast = Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }/*else {
                    toast = Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT);
                    toast.show();
                    intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                }*/
                edUsuario.setText("");
                edPassword.setText("");
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }
}
