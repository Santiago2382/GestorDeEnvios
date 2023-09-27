package ue.edu.co.gestorenvios.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ue.edu.co.gestorenvios.model.Pedido;

public interface ApiInterface {
    @GET("order") // TODO: Actualiza con tu ruta específica.
    Call<List<Pedido>> obtenerPedidosPorUsuario(@Query("usuario_id") int usuarioId);
}
