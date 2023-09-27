package ue.edu.co.gestorenvios.remoto;

import java.util.List;

public class PedidosResponse {
    private boolean success; // Indica si la solicitud fue exitosa o no
    private List<Pedido> pedidos; // Lista de objetos de tipo Pedido

    // Getters y setters para success y pedidos

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
