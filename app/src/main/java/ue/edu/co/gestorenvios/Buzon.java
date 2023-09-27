package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Buzon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);

        ImageButton buttonDevolverse = findViewById(R.id.btnBuzonDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(Buzon.this, Menu.class);
            startActivity(intent);
        });

    }

}
