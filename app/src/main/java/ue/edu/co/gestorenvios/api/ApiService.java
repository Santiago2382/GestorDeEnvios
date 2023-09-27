package ue.edu.co.gestorenvios.api;

import retrofit2.Call;
import retrofit2.http.GET;
import ue.edu.co.gestorenvios.remoto.PedidosResponse;

public interface ApiService {
    // Definir una solicitud GET para obtener pedidos
    @GET("obtener_pedidos.php")
    Call<PedidosResponse> obtenerPedidos();
}
