package com.sw.sgtu;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.Usuario;
import com.sw.sgtu.request.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    int ID_USUARIO=41;

    Intent intent;

    ApiService apiService;
    private final String TAG = RegistroActivity.class.getSimpleName();

    Toast toast;

    EditText edNombre;
    EditText edApellido;
    EditText edNuevoUsuario;
    EditText edNuevoUsuarioPass;
    TextView tvNivelFuerza;

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edNombre = findViewById(R.id.edNombre);
        edApellido = findViewById(R.id.edApellido);
        edNuevoUsuario = findViewById(R.id.edNuevoUsuario);
        edNuevoUsuarioPass = findViewById(R.id.edNuevoPass);
        tvNivelFuerza = findViewById(R.id.tvNivelFuerza);

        edNuevoUsuarioPass.addTextChangedListener(mTextEditorWatcher);
    }

    public void registrar(View view) {
        usuario = new Usuario();
        if(edNombre.getText().toString().isEmpty() ||
                edApellido.getText().toString().isEmpty() ||
                edNuevoUsuario.getText().toString().isEmpty() ||
                edNuevoUsuarioPass.getText().toString().isEmpty()) {
            toast = Toast.makeText(RegistroActivity.this, "Faltan datos", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            usuario.setNombre(edNombre.getText().toString());
            usuario.setApellido(edApellido.getText().toString());
            usuario.setUsuario(edNuevoUsuario.getText().toString());
            usuario.setPassword(edNuevoUsuarioPass.getText().toString());
            registrarUsuario(usuario);
        }
    }

    public void registrarUsuario(Usuario request){
        apiService = ApiAdapter.createService(ApiService.class);
        Call<UserResponse> call = apiService.registrarUsuario(request);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    //System.out.println("BODY==>" + response.body().toString());
                    //ID_USUARIO = response.body().getId_usuario();
                    toast = Toast.makeText(RegistroActivity.this, "Bienvenido", Toast.LENGTH_SHORT);
                    toast.show();
                    intent = new Intent(RegistroActivity.this, PrincipalActivity.class);
                    intent.putExtra("ID_USUARIO", ID_USUARIO);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            // When No Password Entered
            tvNivelFuerza.setText("Not Entered");
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        public void afterTextChanged(Editable s)
        {
            if(s.length()==0){
                tvNivelFuerza.setText("Contraseña vacia");
                tvNivelFuerza.setTextColor(Color.parseColor("#F30E0E"));
            }
            else if(s.length()<6){
                tvNivelFuerza.setText("Facíl");
                tvNivelFuerza.setTextColor(Color.parseColor("#F26D05"));
            }
            else if(s.length()<10){
                tvNivelFuerza.setText("Mediana");
                tvNivelFuerza.setTextColor(Color.parseColor("#E3A41F"));
            }
            else if(s.length()<15){
                tvNivelFuerza.setText("Fuerte");
                tvNivelFuerza.setTextColor(Color.parseColor("#F30E0E"));
            }
            else{
                tvNivelFuerza.setText("Muy segura");
                tvNivelFuerza.setTextColor(Color.parseColor("#1CB626"));
            }

            /*if(s.length()==20)
                tvNivelFuerza.setText("Tamañao maximo de contraseña");*/
        }
    };
}
