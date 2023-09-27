package ue.edu.co.gestorenvios;

// Importaciones de clases y paquetes necesarios
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        // Configuración del botón para volver a la actividad de inicio de sesión (Login)
        ImageButton buttonDevolverse = findViewById(R.id.btnRegistroDevolverse);
        buttonDevolverse.setOnClickListener(view -> {
            Intent intent = new Intent(Registro.this, Login.class);
            startActivity(intent);
        });

        // Asociación de campos de entrada con los elementos de la interfaz de usuario
        etUsuario = findViewById(R.id.etNombreRegistro);
        etPassword = findViewById(R.id.etPassResgistro);
        etNumeroCelular = findViewById(R.id.etNumeroCelular);
        etNit = findViewById(R.id.etNit);
        etCorreo = findViewById(R.id.etCorreo);

        // Configuración del botón para realizar el registro del usuario
        Button buttonRegistro = findViewById(R.id.btnSiguienteRegis);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        // Configuración del botón para volver a la actividad de inicio de sesión (Login)
        Button buttonDevolverce = findViewById(R.id.btnDevolverce);
        buttonDevolverce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });
    }

    // Método para registrar al usuario en el servidor
    public void registerUser() {
        // Validación de los campos de entrada
        if (validateFields()) {
            // URL del servidor al que se enviará la solicitud de registro
            String url = "http://192.168.1.10/api_gestor/register.php";

            // Creación de una solicitud POST de tipo String utilizando Volley
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, response -> {
                // Respuesta del servidor
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String message = jsonResponse.getString("message");
                    Toast.makeText(Registro.this, message, Toast.LENGTH_SHORT).show();

                    // Agregar un registro de log para el mensaje del servidor
                    Log.d("RegistroActivity", "Mensaje del servidor: " + message);

                    // Si el registro es exitoso, redirige a la actividad de inicio de sesión (Login)
                    Intent intent = new Intent(Registro.this, Login.class);
                    startActivity(intent);
                    finish();

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
                    // Parámetros que se enviarán en la solicitud POST
                    Map<String, String> params = new HashMap<>();
                    params.put("usuario", etUsuario.getText().toString());
                    params.put("password", etPassword.getText().toString());
                    params.put("numero_celular", etNumeroCelular.getText().toString());
                    params.put("nit_o_cc", etNit.getText().toString());
                    params.put("correo", etCorreo.getText().toString());
                    return params;
                }
            };

            // Agregar la solicitud a la cola de solicitudes de Volley
            Volley.newRequestQueue(this).add(postRequest);
        }
    }

    // Método para validar que todos los campos estén completos
    private boolean validateFields() {
        if (etUsuario.getText().toString().trim().isEmpty() ||
                etPassword.getText().toString().trim().isEmpty() ||
                etNumeroCelular.getText().toString().trim().isEmpty() ||
                etNit.getText().toString().trim().isEmpty() ||
                etCorreo.getText().toString().trim().isEmpty()) {

            Toast.makeText(Registro.this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
