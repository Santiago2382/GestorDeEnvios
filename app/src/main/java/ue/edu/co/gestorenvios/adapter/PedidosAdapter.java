package ue.edu.co.gestorenvios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ue.edu.co.gestorenvios.R;
import ue.edu.co.gestorenvios.remoto.Pedido;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidoViewHolder> {

    private List<Pedido> listaPedidos;

    public PedidosAdapter(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de pedido
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        // Vincular datos del pedido a los elementos de la vista
        Pedido pedidoActual = listaPedidos.get(position);
        holder.tvUsuarioId.setText("Usuario ID: " + String.valueOf(pedidoActual.getUsuario_id()));
        holder.tvCantidadPaquetes.setText("Cantidad de Paquetes: " + String.valueOf(pedidoActual.getCantidad_paquetes()));
        holder.tvDimensiones.setText("Dimensiones: " + pedidoActual.getDimensiones());
        holder.tvDireccion.setText("Dirección: " + pedidoActual.getDireccion());
        holder.tvCiudadBarrio.setText("Ciudad/Barrio: " + pedidoActual.getCiudad() + "/" + pedidoActual.getBarrio());
        holder.tvNombreReceptor.setText("Nombre del Receptor: " + pedidoActual.getNombre_receptor());
        holder.tvCelularReceptor.setText("Celular del Receptor: " + pedidoActual.getCelular_receptor());
    }

    @Override
    public int getItemCount() {
        // Devuelve la cantidad de elementos en la lista de pedidos
        return listaPedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        // Definir elementos de la vista para mostrar los datos del pedido
        TextView tvUsuarioId;
        TextView tvCantidadPaquetes;
        TextView tvDimensiones;
        TextView tvDireccion;
        TextView tvCiudadBarrio;
        TextView tvNombreReceptor;
        TextView tvCelularReceptor;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar los elementos de la vista
            tvUsuarioId = itemView.findViewById(R.id.tvUsuarioId);
            tvCantidadPaquetes = itemView.findViewById(R.id.tvCantidadPaquetes);
            tvDimensiones = itemView.findViewById(R.id.tvDimensiones);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvCiudadBarrio = itemView.findViewById(R.id.tvCiudadBarrio);
            tvNombreReceptor = itemView.findViewById(R.id.tvNombreReceptor);
            tvCelularReceptor = itemView.findViewById(R.id.tvCelularReceptor);
        }
    }
}
