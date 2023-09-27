package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ue.edu.co.gestorenvios.adapter.PedidosAdapter;
import ue.edu.co.gestorenvios.api.ApiService;
import ue.edu.co.gestorenvios.remoto.Pedido;
import ue.edu.co.gestorenvios.remoto.PedidosResponse;

public class Envios extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Iniciar el Logging Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // Define solo la URL base aquí
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10/api_gestor/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())  // Agregar el cliente con logging
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<PedidosResponse> call = service.obtenerPedidos();

        call.enqueue(new Callback<PedidosResponse>() {
            @Override
            public void onResponse(Call<PedidosResponse> call, Response<PedidosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pedido> pedidos = response.body().getPedidos();
                    PedidosAdapter adapter = new PedidosAdapter(pedidos);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Mostrar el código de respuesta y el mensaje
                    Toast.makeText(Envios.this, "Error " + response.code() + ": " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PedidosResponse> call, Throwable t) {
                Toast.makeText(Envios.this, "Fallo al conectar con el servidor: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}