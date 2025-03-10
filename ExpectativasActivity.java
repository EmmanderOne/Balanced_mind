package com.example.balanced__mind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ExpectativasActivity extends AppCompatActivity {

    private RadioGroup rgExpectativas;
    private EditText etOtraExpectativa;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expectativas);

        // Vincular componentes XML
        rgExpectativas = findViewById(R.id.rgExpectativas);
        etOtraExpectativa = findViewById(R.id.etOtraExpectativa);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Mostrar/ocultar campo "Otra expectativa"
        rgExpectativas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbOtraExpectativa) {
                etOtraExpectativa.setVisibility(View.VISIBLE);
            } else {
                etOtraExpectativa.setVisibility(View.GONE);
            }
        });

        // Manejar clic en "Guardar"
        btnGuardar.setOnClickListener(v -> guardarExpectativas());
    }

    private void guardarExpectativas() {
        String expectativa = obtenerExpectativaSeleccionada();

        if (expectativa.isEmpty()) {
            Toast.makeText(this, "¡Selecciona o escribe una expectativa!", Toast.LENGTH_SHORT).show();
        } else {
            guardarEnSharedPreferences(expectativa);
            Toast.makeText(this, "Expectativas guardadas ✅", Toast.LENGTH_SHORT).show();
            finish(); // Regresar al formulario principal
        }
    }

    private String obtenerExpectativaSeleccionada() {
        int selectedId = rgExpectativas.getCheckedRadioButtonId();

        if (selectedId == R.id.rbMejorarSaludMental) return "Mejorar salud mental";
        if (selectedId == R.id.rbReducirEstres) return "Reducir estrés";
        if (selectedId == R.id.rbManejarAnsiedad) return "Manejar ansiedad";
        if (selectedId == R.id.rbMejorarRelaciones) return "Mejorar relaciones";
        if (selectedId == R.id.rbAumentarAutoestima) return "Aumentar autoestima";
        if (selectedId == R.id.rbLograrMetas) return "Lograr metas personales";
        if (selectedId == R.id.rbEntenderEmociones) return "Entender emociones";
        if (selectedId == R.id.rbHabilidadesSociales) return "Desarrollar habilidades sociales";
        if (selectedId == R.id.rbSuperarTraumas) return "Superar traumas";
        if (selectedId == R.id.rbMejorarRendimiento) return "Mejorar rendimiento";
        if (selectedId == R.id.rbOtraExpectativa) return etOtraExpectativa.getText().toString().trim();

        return ""; // Si no hay selección
    }

    private void guardarEnSharedPreferences(String expectativa) {
        SharedPreferences sharedPref = getSharedPreferences("Formulario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("expectativa", expectativa);
        editor.apply();
    }
}