package ue.edu.co.gestorenvios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    // Declaraciones de los campos de entrada
    EditText etUsuario, etPassword, etNumeroCelular, etNit, etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ImageButton buttonDevolverse = findViewById(R.id.btnRegistroDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(Registro.this, Login.class);
            startActivity(intent);
        });

        // Asociación de campos de entrada
        etUsuario = findViewById(R.id.etNombreRegistro);
        etPassword = findViewById(R.id.etPassResgistro);
        etNumeroCelular = findViewById(R.id.etNumeroCelular);
        etNit = findViewById(R.id.etNit);
        etCorreo = findViewById(R.id.etCorreo);

        Button buttonRegistro = findViewById(R.id.btnSiguienteRegis);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        Button buttonDevolverce = findViewById(R.id.btnDevolverce);
        buttonDevolverce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void registerUser() {

        String url = "http://192.168.1.8/api_gestor/register.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, response -> {


            // Respuesta del servidor
            try {
                JSONObject jsonResponse = new JSONObject(response);
                String message = jsonResponse.getString("message");
                Toast.makeText(Registro.this, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Registro.this, "JSONException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }, error -> {
            // Error en la solicitud
            String errorMessage = error != null ? error.toString() : "Unknown error";
            Toast.makeText(Registro.this, "Error al conectar: " + errorMessage, Toast.LENGTH_SHORT).show();
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", etUsuario.getText().toString());
                params.put("password", etPassword.getText().toString());
                params.put("numero_celular", etNumeroCelular.getText().toString());
                params.put("nit_o_cc", etNit.getText().toString());
                params.put("correo", etCorreo.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);
    }
}

