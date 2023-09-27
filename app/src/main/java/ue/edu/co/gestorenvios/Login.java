package ue.edu.co.gestorenvios;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonRegistrarse = findViewById(R.id.btnRegistrarse);
        buttonRegistrarse.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Registro.class);
            startActivity(intent);
        });

        Button buttonOlvidoPass = findViewById(R.id.btnLoginOlvidoPass);
        buttonOlvidoPass.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, OlvidoPass.class);
            startActivity(intent);
        });

        ImageButton buttonDevolverse = findViewById(R.id.btnLoginDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        });

        Button buttonMenu = findViewById(R.id.btnIngresarApp);
        buttonMenu.setOnClickListener(view -> {
            EditText etCorreo = findViewById(R.id.etCorreoElectronico);
            EditText etPass = findViewById(R.id.etLoginPass);
            String correo = etCorreo.getText().toString();
            String contrasena = etPass.getText().toString();
            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(correo, contrasena);
            }
        });
    }

    private void loginUser(String correo, String contrasena) {
        String url = "http://192.168.1.8/api_gestor/login.php";

        // Crear objeto JSON con los datos a enviar
        JSONObject params = new JSONObject();
        try {
            params.put("correo", correo);
            params.put("contrasena", contrasena);
            Log.d("LoginDebug", "Sending: " + params.toString());  // Línea de depuración
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, url, params,
                response -> {
                    Log.d("LoginResponse", response.toString());
                    try {
                        String message = response.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if (response.has("user")) {
                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        Log.e("LoginError", "Status code: " + error.networkResponse.statusCode + ", Response body: " + responseBody);
                    }
                    Log.e("LoginError", "Error: ", error);
                    Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonRequest);
    }
}
