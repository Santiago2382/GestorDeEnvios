package ue.edu.co.gestorenvios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class Login extends AppCompatActivity {

    private static final String SHARED_PREFS_NAME = "biometric_prefs";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";

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

        ImageButton buttonHuella = findViewById(R.id.btnHuella);
        buttonHuella.setOnClickListener(view -> {
            if (checkBiometricSupport()) {
                authenticateUser();
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
                            saveUserLoggedInState(true);  // Guardamos que el usuario ha iniciado sesión
                            Intent intent = new Intent(Login.this, Menu.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show()) {
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

    private boolean checkBiometricSupport() {
        PackageManager packageManager = this.getPackageManager();
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            Toast.makeText(getApplicationContext(), "El dispositivo no soporta autenticación por huella", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permiso de autenticación por huella no otorgado", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void authenticateUser() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                if (isUserLoggedIn()) {
                    Intent intent = new Intent(Login.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, inicie sesión primero", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Autenticación fallida", Toast.LENGTH_SHORT).show();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Ingreso con Huella")
                .setDescription("Usa tu huella para ingresar a la aplicación")
                .setNegativeButtonText("Cancelar")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private SharedPreferences getEncryptedSharedPreferences() {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            return EncryptedSharedPreferences.create(
                    SHARED_PREFS_NAME,
                    masterKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveUserLoggedInState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getEncryptedSharedPreferences();
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(KEY_USER_LOGGED_IN, isLoggedIn).apply();
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getEncryptedSharedPreferences();
        return sharedPreferences != null && sharedPreferences.getBoolean(KEY_USER_LOGGED_IN, false);
    }
}
