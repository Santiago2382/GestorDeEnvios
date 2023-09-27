package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SolicitarMercancia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_mercancia);

        ImageButton buttonDevolverse = findViewById(R.id.btnMercanciaDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(SolicitarMercancia.this, Menu.class);
            startActivity(intent);
        });
    }
}