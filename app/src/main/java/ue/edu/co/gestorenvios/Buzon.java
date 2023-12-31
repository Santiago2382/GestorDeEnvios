package ue.edu.co.gestorenvios;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;

public class Buzon extends AppCompatActivity {

    // Declaraciones de los campos de entrada
    private EditText etIdUsuario2, etNombreCompleto, etCorreo, etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);

        // Asociación de campos de entrada
        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        etCorreo = findViewById(R.id.etCorreoElectronico);
        etMensaje = findViewById(R.id.etMensaje);
        etIdUsuario2 = findViewById(R.id.etIdUsuario2);

        ImageButton buttonDevolverse = findViewById(R.id.btnBuzonDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            // Volver a la actividad del menú
            Intent intent = new Intent(Buzon.this, Menu.class);
            startActivity(intent);
            finish(); // Cerrar la actividad actual
        });

        Button btnEnviarSugerencia = findViewById(R.id.btnEnviar);
        btnEnviarSugerencia.setOnClickListener(view -> enviarSugerencia());
    }

    private void enviarSugerencia() {
        String url = "http://192.168.1.10/api_gestor/sugerencias.php";  // Reemplaza con tu URL de endpoint para las sugerencias

        // Validar que todos los campos estén completos antes de enviar
        if (!validateFields()) {
            Toast.makeText(Buzon.this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un objeto JSON con los datos de la sugerencia
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("usuario_id", etIdUsuario2.getText().toString());
            jsonBody.put("nombre_completo", etNombreCompleto.getText().toString());
            jsonBody.put("correo", etCorreo.getText().toString());
            jsonBody.put("mensaje", etMensaje.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Crear una solicitud HTTP POST con Volley para enviar la sugerencia
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, response -> {
            try {
                String message = response.getString("message");
                Toast.makeText(Buzon.this, message, Toast.LENGTH_SHORT).show();

                // Después de enviar la sugerencia, puedes redirigir al usuario al menú principal u otra actividad
                Intent intent = new Intent(Buzon.this, Menu.class);
                startActivity(intent);
                finish(); // Cerrar la actividad actual
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Buzon.this, "JSONException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            String errorMessage = error != null ? error.toString() : "Unknown error";
            Toast.makeText(Buzon.this, "Error al conectar: " + errorMessage, Toast.LENGTH_SHORT).show();
        });

        // Agregar la solicitud a la cola de Volley para ser ejecutada
        Volley.newRequestQueue(this).add(postRequest);
    }

    private boolean validateFields() {
        // Validar que los campos no estén vacíos
        return !(etIdUsuario2.getText().toString().trim().isEmpty() ||
                etNombreCompleto.getText().toString().trim().isEmpty() ||
                etCorreo.getText().toString().trim().isEmpty() ||
                etMensaje.getText().toString().trim().isEmpty());
    }
}
