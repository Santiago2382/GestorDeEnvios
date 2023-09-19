package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonOlvidoPass = findViewById(R.id.btnLoginOlvidoPass);
        buttonOlvidoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Login.this, OlvidoPass.class);
                startActivity(intent);
            }
        });

        Button buttonRegistro = findViewById(R.id.btnRegistrarse);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });

        Button buttonMenu = findViewById(R.id.btnIngresarApp);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Login.this, Menu.class);
                startActivity(intent);
            }
        });

    }

}