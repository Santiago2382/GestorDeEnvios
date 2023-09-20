
package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button buttonDevolverce = findViewById(R.id.btnDevolverce);
        buttonDevolverce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la segunda actividad aquí
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });
    }

}