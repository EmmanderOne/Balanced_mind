package com.example.balanced__mind;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class FormularioActivity extends AppCompatActivity {

    private Button btnEnviarFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button btDatosPersonales = findViewById(R.id.btDatosPersonales);
        Button btAntecedentes = findViewById(R.id.btAntecedentes);
        Button btMotivoConsulta = findViewById(R.id.btMotivoConsulta);
        Button btExpectativas = findViewById(R.id.btExpectativas);
        btnEnviarFormulario = findViewById(R.id.btnEnviarFormulario);

        // Deshabilitar el botón de enviar inicialmente
        btnEnviarFormulario.setEnabled(false);

        // Navegar a DatosPersonalesActivity
        btDatosPersonales.setOnClickListener(v -> {
            Intent intent = new Intent(FormularioActivity.this, com.example.balanced__mind.DatosPersonalesActivity.class);
            startActivity(intent);
        });

        // Navegar a AntecedentesActivity
        btAntecedentes.setOnClickListener(v -> {
            Intent intent = new Intent(FormularioActivity.this, com.example.balanced__mind.AntecedentesActivity.class);
            startActivity(intent);
        });

        // Navegar a MotivoConsultaActivity
        btMotivoConsulta.setOnClickListener(v -> {
            Intent intent = new Intent(FormularioActivity.this, com.example.balanced__mind.MotivoConsultaActivity.class);
            startActivity(intent);
        });

        // Navegar a ExpectativasActivity
        btExpectativas.setOnClickListener(v -> {
            Intent intent = new Intent(FormularioActivity.this, com.example.balanced__mind.ExpectativasActivity.class);
            startActivity(intent);
        });

        // Enviar formulario cuando todas las secciones estén completas
        btnEnviarFormulario.setOnClickListener(v -> {
            // Aquí iría la lógica para enviar el formulario completo
            // Por ejemplo, guardar en una base de datos o enviar a un servidor

            // Redirigir a CitasActivity
            Intent intent = new Intent(FormularioActivity.this, CitasPacienteActivity.class);
            startActivity(intent);
            finish(); // Cierra FormularioActivity para que no se pueda volver atrás
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Verificar si todas las secciones están completas
        SharedPreferences sharedPref = getSharedPreferences("Formulario", Context.MODE_PRIVATE);
        String datosPersonales = sharedPref.getString("datosPersonales", null);
        String antecedentes = sharedPref.getString("antecedentes", null);
        String motivoConsulta = sharedPref.getString("motivoConsulta", null);
        String expectativas = sharedPref.getString("expectativas", null);

        // Habilitar el botón de enviar si todas las secciones están completas
        if (datosPersonales != null && antecedentes != null && motivoConsulta != null && expectativas != null) {
            btnEnviarFormulario.setEnabled(true);
        }
    }
}