package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterTienda extends RecyclerView.Adapter<AdapterTienda.viewholdertiendas>  {

    List<Tienda> tiendaList;

    public AdapterTienda(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    @NonNull
    @Override
    public viewholdertiendas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate with row
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tienda, parent, false);
        viewholdertiendas holder = new viewholdertiendas(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdertiendas holder, int position) {
        Tienda tienda = tiendaList.get(position);

        holder.tv_nombre.setText(tienda.getNombre());
        holder.tv_descripcion.setText(tienda.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return tiendaList.size();
    }

    public class viewholdertiendas extends RecyclerView.ViewHolder {
        TextView tv_nombre, tv_descripcion, tv_dulces;
        public viewholdertiendas(@NonNull View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);

        }
    }
}
