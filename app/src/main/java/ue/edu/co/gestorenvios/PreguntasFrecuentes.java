package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PreguntasFrecuentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_frecuentes);

        // Configurar el botón para volver al menú principal
        Button buttonMenu = findViewById(R.id.btnVolverPreguntas);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad del menú principal (Menu.class)
                Intent intent = new Intent(PreguntasFrecuentes.this, Menu.class);
                startActivity(intent);
            }
        });
    }
}
