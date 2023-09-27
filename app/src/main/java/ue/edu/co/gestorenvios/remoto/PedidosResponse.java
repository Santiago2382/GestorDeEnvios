package ue.edu.co.gestorenvios.remoto;

import java.util.List;

public class PedidosResponse {
    private boolean success;
    private List<Pedido> pedidos;

    // Getters y setters

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