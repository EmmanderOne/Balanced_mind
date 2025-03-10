package com.example.balanced__mind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class RegisterPsicologoActivity extends AppCompatActivity {

    EditText etNombreCompleto, etTelefono, etNumeroCedula, etEspecialidad, etCorreo, etContrasena, etDomicilio, etHorario;
    CheckBox cbTerminos;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_psicologo);

        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        etNumeroCedula = findViewById(R.id.etNumeroCedula);
        etEspecialidad = findViewById(R.id.etEspecialidad);
        etContrasena = findViewById(R.id.etContrasena);
        etDomicilio = findViewById(R.id.etDomicilio);
        etHorario = findViewById(R.id.etHorario);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> {
            String nombreCompleto = etNombreCompleto.getText().toString();
            String correo = etCorreo.getText().toString();
            String telefono = etTelefono.getText().toString();
            String numeroCedula = etNumeroCedula.getText().toString();
            String especialidad = etEspecialidad.getText().toString();
            String contrasena = etContrasena.getText().toString();
            String domicilio = etDomicilio.getText().toString();
            String horario = etHorario.getText().toString();
            boolean aceptaTerminos = cbTerminos.isChecked();

            if (nombreCompleto.isEmpty() || correo.isEmpty() || telefono.isEmpty() || numeroCedula.isEmpty() ||
                    especialidad.isEmpty() || contrasena.isEmpty() || domicilio.isEmpty() || horario.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!aceptaTerminos) {
                Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterPsicologoActivity.this, com.example.balanced__mind.LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}