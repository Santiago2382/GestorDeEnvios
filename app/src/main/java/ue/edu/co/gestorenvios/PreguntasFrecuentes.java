package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreguntasFrecuentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_frecuentes);

        Button buttonMenu = findViewById(R.id.btnVolverPreguntas);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad Menu aquí
                Intent intent = new Intent(PreguntasFrecuentes.this, Menu.class);
                startActivity(intent);
            }
        });

        Button buttonPregunta1 = findViewById(R.id.btnPregunta1);
        buttonPregunta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad RespuestaPregunta1 aquí
                Intent intent = new Intent(PreguntasFrecuentes.this, RespuestaPregunta1.class);
                startActivity(intent);
            }
        });

        Button buttonPregunta2 = findViewById(R.id.btnPregunta2);
        buttonPregunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad RespuestaPregunta2 aquí
                Intent intent = new Intent(PreguntasFrecuentes.this, RespuestaPregunta2.class);
                startActivity(intent);
            }
        });

        Button buttonPregunta3 = findViewById(R.id.btnPregunta3);
        buttonPregunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad RespuestaPregunta3 aquí
                Intent intent = new Intent(PreguntasFrecuentes.this, RespuestaPregunta3.class);
                startActivity(intent);
            }
        });

    }
}
