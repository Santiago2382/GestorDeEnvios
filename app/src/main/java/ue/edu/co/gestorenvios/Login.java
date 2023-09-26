package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

                EditText etCorreo = findViewById(R.id.etCorreoElectronico);
                EditText etPass = findViewById(R.id.etLoginPass);
                String usuario = etCorreo.getText().toString();
                String contrasena = etPass.getText().toString();


                Intent intent = new Intent(Login.this, OlvidoPass.class);
                startActivity(intent);
                loginUser(usuario, contrasena);
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

    public void loginUser(String usuario, String contrasena) {
        String url = "http://192.168.1.10/api_gestor/login.php";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, response -> {
            // Respuesta del servidor
            try {
                JSONObject jsonResponse = new JSONObject(response);
                String message = jsonResponse.getString("message");
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                if (jsonResponse.has("user")) {
                    // Aquí puedes manejar la información del usuario si es necesario
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            // Error en la solicitud
            Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("contrasena", contrasena);
                return params;
            }
        };

        queue.add(postRequest);
    }

}