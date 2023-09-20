package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonSolicitarMercancia = findViewById(R.id.btnSolicitarMercancia);
        buttonSolicitarMercancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Menu.this, SolicitarMercancia.class);
                startActivity(intent);
            }
        });

        Button buttonPreguntasFrecuentes = findViewById(R.id.btnPreguntasFrecuentes);
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