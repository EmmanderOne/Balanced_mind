package com.example.balanced__mind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;

public class CitasPsicologoActivity extends AppCompatActivity {

    private LinearLayout contenedorCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_psicologo);

        contenedorCitas = findViewById(R.id.contenedorCitas);
        cargarCitas();
    }

    private void cargarCitas() {
        SharedPreferences sharedPref = getSharedPreferences("Citas", Context.MODE_PRIVATE);
        Map<String, ?> todasCitas = sharedPref.getAll();

        contenedorCitas.removeAllViews();

        for(Map.Entry<String, ?> entrada : todasCitas.entrySet()) {
            String[] datos = entrada.getValue().toString().split("\\|");
            if(datos.length == 4) {
                agregarCitaALista(datos[0], datos[1], datos[2], datos[3], entrada.getKey());
            }
        }
    }

    private void agregarCitaALista(String fecha, String hora, String motivo, String estado, String key) {
        View itemCita = getLayoutInflater().inflate(R.layout.item_cita_psicologo, null);

        TextView tvDetalles = itemCita.findViewById(R.id.tvDetallesCita);
        Button btnAceptar = itemCita.findViewById(R.id.btnAceptarCita);
        Button btnRechazar = itemCita.findViewById(R.id.btnRechazarCita);

        String detalles = String.format("Fecha: %s\nHora: %s\nMotivo: %s\nEstado: %s",
                fecha, hora, motivo, estado);
        tvDetalles.setText(detalles);

        btnAceptar.setOnClickListener(v -> cambiarEstadoCita(key, "Aceptada"));
        btnRechazar.setOnClickListener(v -> cambiarEstadoCita(key, "Rechazada"));

        contenedorCitas.addView(itemCita);
    }

    private void cambiarEstadoCita(String key, String nuevoEstado) {
        SharedPreferences sharedPref = getSharedPreferences("Citas", Context.MODE_PRIVATE);
        String citaActual = sharedPref.getString(key, "");
        String[] partes = citaActual.split("\\|");

        if(partes.length == 4) {
            String nuevaCita = String.format("%s|%s|%s|%s",
                    partes[0], partes[1], partes[2], nuevoEstado);

            sharedPref.edit().putString(key, nuevaCita).apply();
            cargarCitas();
        }
    }
}