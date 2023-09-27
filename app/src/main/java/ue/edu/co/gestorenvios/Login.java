package ue.edu.co.gestorenvios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
        String url = "http://192.168.1.10/api_gestor/login.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        String message = jsonResponse.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if (jsonResponse.has("user")) {
                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("correo", correo);
                params.put("contrasena", contrasena);
                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();  // Cierra todas las actividades y sale de la app
    }
}
