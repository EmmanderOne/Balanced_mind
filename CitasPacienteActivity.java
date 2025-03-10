package com.example.balanced__mind;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class CitasPacienteActivity extends AppCompatActivity {

    private EditText etFecha, etHora, etMotivo;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_paciente);

        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        etMotivo = findViewById(R.id.etMotivo);
        calendar = Calendar.getInstance();

        configurarSelectores();
    }

    private void configurarSelectores() {
        // Selector de fecha
        etFecha.setOnClickListener(v -> new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int day) -> {
                    calendar.set(year, month, day);
                    etFecha.setText(String.format("%02d/%02d/%04d", day, month+1, year));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show());

        // Selector de hora
        etHora.setOnClickListener(v -> new TimePickerDialog(
                this,
                (TimePicker view, int hour, int minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    etHora.setText(String.format("%02d:%02d", hour, minute));
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        ).show());
    }

    public void solicitarCita(View view) {
        if(validarCampos()) {
            guardarCita();
            Toast.makeText(this, "Cita solicitada con éxito", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validarCampos() {
        if(etFecha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione una fecha", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etHora.getText().toString().isEmpty()) {
            Toast.makeText(this, "Seleccione una hora", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etMotivo.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Describa el motivo", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void guardarCita() {
        SharedPreferences sharedPref = getSharedPreferences("Citas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String cita = String.format("%s|%s|%s|Pendiente",
                etFecha.getText().toString(),
                etHora.getText().toString(),
                etMotivo.getText().toString().trim());

        editor.putString("CITA_" + System.currentTimeMillis(), cita);
        editor.apply();
    }
}