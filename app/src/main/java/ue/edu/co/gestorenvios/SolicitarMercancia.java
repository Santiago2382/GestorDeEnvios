package ue.edu.co.gestorenvios;

// Importaciones de clases y paquetes necesarios
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

public class SolicitarMercancia extends AppCompatActivity {

    // Declaraciones de los campos de entrada
    private EditText etIdUsuario, etCantidadPaquetes, etDimensionAlto, etDimensionAncho, etDireccionPaquete, etCiudadPaquete, etBarrioPaquete, etNombreRecibe, etCelularRecibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_mercancia);

        // Asociación de campos de entrada con los elementos de la interfaz de usuario
        etCantidadPaquetes = findViewById(R.id.etCantidadPaquetes);
        etDimensionAlto = findViewById(R.id.etDimensionAlto);
        etDimensionAncho = findViewById(R.id.etDimensionAncho);
        etDireccionPaquete = findViewById(R.id.etDireccionPaquete);
        etCiudadPaquete = findViewById(R.id.etCiudadPaquete);
        etBarrioPaquete = findViewById(R.id.etBarrioPaquete);
        etNombreRecibe = findViewById(R.id.etNombreRecibe);
        etCelularRecibe = findViewById(R.id.etCelularRecibe);
        etIdUsuario = findViewById(R.id.etIdUsuario);

        // Configuración del botón para devolverse a la actividad de menú
        ImageButton buttonDevolverse = findViewById(R.id.btnMercanciaDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(SolicitarMercancia.this, Menu.class);
            startActivity(intent);
            finish(); // Cerrar la actividad actual
        });

        // Configuración del botón para solicitar mercancía
        Button btnSolicitarMercancia = findViewById(R.id.btnSolicitarMercancia);
        btnSolicitarMercancia.setOnClickListener(view -> solicitarMercancia());
    }

    // Método para enviar una solicitud de mercancía al servidor
    private void solicitarMercancia() {
        // URL del servidor al que se enviará la solicitud
        String url = "http://192.168.1.10/api_gestor/order.php";

        // Creación de un objeto JSON que contiene los datos de la solicitud
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("usuario_id", etIdUsuario.getText().toString());
            jsonBody.put("cantidad_paquetes", etCantidadPaquetes.getText().toString());
            jsonBody.put("alto", etDimensionAlto.getText().toString());
            jsonBody.put("ancho", etDimensionAncho.getText().toString());
            jsonBody.put("direccion", etDireccionPaquete.getText().toString());
            jsonBody.put("ciudad", etCiudadPaquete.getText().toString());
            jsonBody.put("barrio", etBarrioPaquete.getText().toString());
            jsonBody.put("nombre_receptor", etNombreRecibe.getText().toString());
            jsonBody.put("celular_receptor", etCelularRecibe.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Creación de una solicitud JSON POST utilizando Volley
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, response -> {
            try {
                // Manejo de la respuesta del servidor
                String message = response.getString("message");
                Toast.makeText(SolicitarMercancia.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SolicitarMercancia.this, Menu.class);
                startActivity(intent);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(SolicitarMercancia.this, "JSONException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // Manejo de errores de la solicitud
            String errorMessage = error != null ? error.toString() : "Unknown error";
            Toast.makeText(SolicitarMercancia.this, "Error al conectar: " + errorMessage, Toast.LENGTH_SHORT).show();
        });

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(postRequest);
    }
}
