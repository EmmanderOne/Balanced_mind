package com.example.balanced__mind;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class DatosPersonalesActivity extends AppCompatActivity {

    EditText etNombres, etEdad, etFechaNacimiento,
            etNacimiento, etOcupacion, etEstadoCivil,
            etCalle, etNumero, etColonia, etCiudad, etEstado, etCodigoPostal,
            etTelefonoCelular;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.balanced__mind.R.layout.activity_datos_personales);

        etNombres = findViewById(R.id.etNombres);
        etEdad = findViewById(R.id.etEdad);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etNacimiento = findViewById(R.id.etNacimiento);
        etOcupacion = findViewById(R.id.etOcupacion);
        etEstadoCivil = findViewById(R.id.etEstadoCivil);
        etCalle = findViewById(R.id.etCalle);
        etNumero = findViewById(R.id.etNumero);
        etColonia = findViewById(R.id.etColonia);
        etCiudad = findViewById(R.id.etCiudad);
        etEstado = findViewById(R.id.etEstado);
        etCodigoPostal = findViewById(R.id.etCodigoPostal);
        etTelefonoCelular = findViewById(R.id.etTelefonoCelular);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombres = etNombres.getText().toString();
            String edad = etEdad.getText().toString();
            String fechaNacimiento = etFechaNacimiento.getText().toString();
            String lugarNacimiento = etNacimiento.getText().toString();
            String ocupacion = etOcupacion.getText().toString();
            String estadoCivil = etEstadoCivil.getText().toString();
            String calle = etCalle.getText().toString();
            String numero = etNumero.getText().toString();
            String colonia = etColonia.getText().toString();
            String ciudad = etCiudad.getText().toString();
            String estado = etEstado.getText().toString();
            String codigoPostal = etCodigoPostal.getText().toString();
            String telefonoCelular = etTelefonoCelular.getText().toString();


            // Guardar en SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("Formulario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            String datos = nombres + "," + edad + "," +
                    fechaNacimiento + "," + lugarNacimiento + "," +
                    ocupacion + "," + estadoCivil + "," + calle + "," + numero + "," + colonia + "," +
                    ciudad + "," + estado + "," + codigoPostal + "," + telefonoCelular + ",";
            editor.putString("datosPersonales", datos);
            editor.apply();

            // Mostrar mensaje de éxito
            Toast.makeText(DatosPersonalesActivity.this, "Respuestas guardadas ✅", Toast.LENGTH_SHORT).show();


            // Regresar a FormularioActivity
            finish();
        });
    }
}