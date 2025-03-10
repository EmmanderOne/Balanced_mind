package com.example.balanced__mind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etContrasena = findViewById(R.id.etContrasena);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegistro = findViewById(R.id.tvRegistro);

        tvRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, com.example.balanced__mind.RoleActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString();
            String contrasena = etContrasena.getText().toString();


                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();

        });
    }
}