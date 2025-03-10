package com.example.balanced__mind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AntecedentesActivity extends AppCompatActivity {

    RadioGroup rgAntecedentesPersonales, rgAntecedentesFamiliares;
    EditText etOtroAntecedente, etFamiliarEspecifico;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antecedentes);

        rgAntecedentesPersonales = findViewById(R.id.rgAntecedentesPersonales);
        rgAntecedentesFamiliares = findViewById(R.id.rgAntecedentesFamiliares);
        etOtroAntecedente = findViewById(R.id.etOtroAntecedente);
        etFamiliarEspecifico = findViewById(R.id.etFamiliarEspecifico);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Mostrar u ocultar el campo "Otro antecedente" según la selección
        rgAntecedentesPersonales.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbOtroAntecedente) {
                    etOtroAntecedente.setVisibility(View.VISIBLE);
                } else {
                    etOtroAntecedente.setVisibility(View.GONE);
                }
            }
        });

        // Mostrar u ocultar el campo "Familiar específico" según la selección
        rgAntecedentesFamiliares.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSiFamilia) {
                    etFamiliarEspecifico.setVisibility(View.VISIBLE);
                } else {
                    etFamiliarEspecifico.setVisibility(View.GONE);
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el antecedente personal seleccionado
                int selectedAntecedenteId = rgAntecedentesPersonales.getCheckedRadioButtonId();
                String antecedentePersonal = "";

                if (selectedAntecedenteId == R.id.rbDoloresCabeza) {
                    antecedentePersonal = "Dolores de cabeza";
                } else if (selectedAntecedenteId == R.id.rbDoloresEstomago) {
                    antecedentePersonal = "Dolores de estómago";
                } else if (selectedAntecedenteId == R.id.rbMareos) {
                    antecedentePersonal = "Mareos";
                } else if (selectedAntecedenteId == R.id.rbOtroAntecedente) {
                    antecedentePersonal = etOtroAntecedente.getText().toString();
                }

                // Obtener la respuesta sobre antecedentes familiares
                int selectedFamiliaId = rgAntecedentesFamiliares.getCheckedRadioButtonId();
                String antecedentesFamiliares = "";

                if (selectedFamiliaId == R.id.rbSiFamilia) {
                    antecedentesFamiliares = "Sí, " + etFamiliarEspecifico.getText().toString();
                } else if (selectedFamiliaId == R.id.rbNoFamilia) {
                    antecedentesFamiliares = "No";
                } else if (selectedFamiliaId == R.id.rbNoSeFamilia) {
                    antecedentesFamiliares = "No sé";
                }

                // Validar que todos los campos estén completos
                if (antecedentePersonal.isEmpty() || antecedentesFamiliares.isEmpty()) {
                    Toast.makeText(AntecedentesActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar en SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("Formulario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("antecedentePersonal", antecedentePersonal);
                    editor.putString("antecedentesFamiliares", antecedentesFamiliares);
                    editor.apply();

                    // Mostrar mensaje de éxito
                    Toast.makeText(AntecedentesActivity.this, "Respuestas guardadas ✅", Toast.LENGTH_SHORT).show();

                    // Regresar a FormularioActivity
                    finish();
                }
            }
        });
    }
}