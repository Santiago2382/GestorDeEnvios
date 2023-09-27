package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;
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

public class OlvidoPass extends AppCompatActivity {

    private EditText etCorreo, etNuevaPass, etConfirmarPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvido_pass);

        etCorreo = findViewById(R.id.etCorreo);
        etNuevaPass = findViewById(R.id.etNuevaPass);
        etConfirmarPass = findViewById(R.id.etConfirmarPass);

        ImageButton buttonDevolverse = findViewById(R.id.btnOlvidoPassDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(OlvidoPass.this, Login.class);
            startActivity(intent);
        });

        Button buttonConfirmar = findViewById(R.id.btnConfirmarPass);
        buttonConfirmar.setOnClickListener(view -> {
            String correo = etCorreo.getText().toString().trim();
            String nuevaPass = etNuevaPass.getText().toString().trim();
            String confirmarPass = etConfirmarPass.getText().toString().trim();

            cambiarContrasena(correo, nuevaPass, confirmarPass);
        });
    }

    private void cambiarContrasena(String correo, String nuevaPass, String confirmarPass) {
        String URL = "http://192.168.1.10/api_gestor/nueva_contrasena.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int success = jsonObject.getInt("success");
                        String message = jsonObject.getString("message");
                        Intent intent = new Intent(OlvidoPass.this, Login.class);
                        startActivity(intent);
                        finish();
                        if(success == 1) {
                            Toast.makeText(OlvidoPass.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OlvidoPass.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(OlvidoPass.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(OlvidoPass.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("correo", correo);
                params.put("nuevaPass", nuevaPass);
                params.put("confirmarPass", confirmarPass);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
