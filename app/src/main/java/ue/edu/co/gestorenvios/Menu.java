package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton buttonDevolverse = findViewById(R.id.btnMenuDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(Menu.this, Login.class);
            startActivity(intent);
        });

        ImageButton buttonHistorialEnvios = findViewById(R.id.btnHistorialEnvios);
        buttonHistorialEnvios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Menu.this, Envios.class);
                startActivity(intent);
            }
        });

        ImageButton buttonSolicitarMercancia = findViewById(R.id.btnSolicitarMercancia);
        buttonSolicitarMercancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Menu.this, SolicitarMercancia.class);
                startActivity(intent);
            }
        });

        ImageButton buttonBuzonSugerencias = findViewById(R.id.btnBuzonSugerencias);
        buttonBuzonSugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Menu.this, Buzon.class);
                startActivity(intent);
            }
        });

        ImageButton buttonPreguntasFrecuentes = findViewById(R.id.btnPreguntasFrecuentes);
        buttonPreguntasFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Menu.this, PreguntasFrecuentes.class);
                startActivity(intent);
            }
        });

    }
}