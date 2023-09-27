package ue.edu.co.gestorenvios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ue.edu.co.gestorenvios.api.ApiInterface;
import ue.edu.co.gestorenvios.model.Pedido;

public class Envios extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private int usuarioId = 1; // TODO: Cambiar con el ID de usuario actual.

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);
/*
        // Configuración de Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://tu-direccion-base-del-api/") // TODO: Añade tu dirección base del API.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        obtenerPedidosPorUsuario();

        Button buttonVolver = findViewById(R.id.btnVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Envios.this, Menu.class);
                startActivity(intent);
            }
        });
    }

    private void obtenerPedidosPorUsuario() {
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Pedido>> call = apiInterface.obtenerPedidosPorUsuario(usuarioId);

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pedido> pedidos = response.body();

                    // Configura tu RecyclerView con los pedidos.
                    // TODO: Deberías tener un adaptador para tu RecyclerView que tome la lista de pedidos y la muestre.
                    PedidoAdapter adapter = new PedidoAdapter(pedidos);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                // Maneja el error. Puedes mostrar un mensaje al usuario.
            }
        });

 */
    }
}
