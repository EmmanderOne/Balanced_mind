package com.example.balanced__mind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Button btnPaciente = findViewById(R.id.btnPaciente);
        Button btnPsicologo = findViewById(R.id.btnPsicologo);

        btnPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(RoleActivity.this, com.example.balanced__mind.RegisterActivity.class);
            startActivity(intent);
        });

        btnPsicologo.setOnClickListener(v -> {
            Intent intent = new Intent(RoleActivity.this, com.example.balanced__mind.RegisterPsicologoActivity.class);
            startActivity(intent);
        });
    }
}