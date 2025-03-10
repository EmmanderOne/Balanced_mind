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

public class MotivoConsultaActivity extends AppCompatActivity {

    RadioGroup rgMotivoPrincipal, rgAfectacion, rgAcciones;
    EditText etOtroMotivo, etOtraAfectacion, etOtraAccion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivo_consulta);

        rgMotivoPrincipal = findViewById(R.id.rgMotivoPrincipal);
        rgAfectacion = findViewById(R.id.rgAfectacion);
        rgAcciones = findViewById(R.id.rgAcciones);
        etOtroMotivo = findViewById(R.id.etOtroMotivo);
        etOtraAfectacion = findViewById(R.id.etOtraAfectacion);
        etOtraAccion = findViewById(R.id.etOtraAccion);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Mostrar u ocultar el campo "Otro motivo" según la selección
        rgMotivoPrincipal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbOtroMotivo) {
                    etOtroMotivo.setVisibility(View.VISIBLE);
                } else {
                    etOtroMotivo.setVisibility(View.GONE);
                }
            }
        });

        // Mostrar u ocultar el campo "Otra afectación" según la selección
        rgAfectacion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbOtraAfectacion) {
                    etOtraAfectacion.setVisibility(View.VISIBLE);
                } else {
                    etOtraAfectacion.setVisibility(View.GONE);
                }
            }
        });
        // Mostrar u ocultar el campo "Otra acción" según la selección
        rgAcciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbOtraAccion) {
                    etOtraAccion.setVisibility(View.VISIBLE);
                } else {
                    etOtraAccion.setVisibility(View.GONE);
                }
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el motivo principal seleccionado
                int selectedMotivoId = rgMotivoPrincipal.getCheckedRadioButtonId();
                String motivoPrincipal = "";

                if (selectedMotivoId == R.id.rbAnsiedad) {
                    motivoPrincipal = "Ansiedad";
                } else if (selectedMotivoId == R.id.rbDepresion) {
                    motivoPrincipal = "Depresión";
                } else if (selectedMotivoId == R.id.rbEstres) {
                    motivoPrincipal = "Estrés";
                } else if (selectedMotivoId == R.id.rbOtroMotivo) {
                    motivoPrincipal = etOtroMotivo.getText().toString();
                }

                // Obtener la afectación seleccionada
                int selectedAfectacionId = rgAfectacion.getCheckedRadioButtonId();
                String afectacion = "";

                if (selectedAfectacionId == R.id.rbLaboral) {
                    afectacion = "Área laboral o académica";
                } else if (selectedAfectacionId == R.id.rbRelaciones) {
                    afectacion = "Relaciones personales";
                } else if (selectedAfectacionId == R.id.rbSaludFisica) {
                    afectacion = "Salud física";
                } else if (selectedAfectacionId == R.id.rbSaludEmocional) {
                    afectacion = "Salud emocional";
                } else if (selectedAfectacionId == R.id.rbAutocuidado) {
                    afectacion = "Autocuidado";
                } else if (selectedAfectacionId == R.id.rbFinanzas) {
                    afectacion = "Finanzas";
                } else if (selectedAfectacionId == R.id.rbOtraAfectacion) {
                    afectacion = etOtraAfectacion.getText().toString();
                }

                // Obtener las acciones seleccionadas
                int selectedAccionId = rgAcciones.getCheckedRadioButtonId();
                String accion = "";

                if (selectedAccionId == R.id.rbHablarAmigos) {
                    accion = "Hablé con amigos o familiares";
                } else if (selectedAccionId == R.id.rbBuscarInformacion) {
                    accion = "Busqué información en internet";
                } else if (selectedAccionId == R.id.rbConsultarProfesional) {
                    accion = "Consulté a un profesional";
                } else if (selectedAccionId == R.id.rbRelajacion) {
                    accion = "Practiqué técnicas de relajación";
                } else if (selectedAccionId == R.id.rbCambiosEstiloVida) {
                    accion = "Hice cambios en mi estilo de vida";
                } else if (selectedAccionId == R.id.rbNada) {
                    accion = "Nada, no he hecho nada";
                } else if (selectedAccionId == R.id.rbOtraAccion) {
                    accion = etOtraAccion.getText().toString();
                }


                // Validar que todos los campos estén completos
                if (motivoPrincipal.isEmpty() || afectacion.isEmpty() || accion.isEmpty()) {
                    Toast.makeText(MotivoConsultaActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar en SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("Formulario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("motivoPrincipal", motivoPrincipal);
                    editor.putString("afectacion", afectacion);
                    editor.putString("acciones", accion);
                    editor.apply();

                    // Mostrar mensaje de éxito
                    Toast.makeText(MotivoConsultaActivity.this, "Respuestas guardadas ✅", Toast.LENGTH_SHORT).show();

                    // Regresar a FormularioActivity
                    finish();
                }
            }
        });
    }
}