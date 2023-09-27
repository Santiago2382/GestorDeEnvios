package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class OlvidoPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvido_pass);

        ImageButton buttonDevolverse = findViewById(R.id.btnOlvidoPassDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(OlvidoPass.this, Login.class);
            startActivity(intent);
        });

        Button buttonConfirmar = findViewById(R.id.btnConfirmarPass);
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OlvidoPass.this, Login.class);
                startActivity(intent);
            }
        });

    }
}