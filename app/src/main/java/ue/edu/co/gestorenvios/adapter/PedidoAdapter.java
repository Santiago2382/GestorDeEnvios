package ue.edu.co.gestorenvios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ue.edu.co.gestorenvios.R;
import ue.edu.co.gestorenvios.model.Pedido;

/*

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<Pedido> pedidos;

    public PedidoAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_item, parent, false);
        return new PedidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.textViewDireccion.setText(pedido.getDireccion());
        // TODO: Completa con cualquier otro dato del pedido que quieras mostrar.
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDireccion;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDireccion = itemView.findViewById(R.id.textViewDireccion);
            // TODO: Inicializa cualquier otro elemento de UI aquí
        }
    }
}


 */
