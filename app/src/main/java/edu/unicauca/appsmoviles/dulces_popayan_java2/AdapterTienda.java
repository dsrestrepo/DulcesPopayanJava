package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterTienda extends RecyclerView.Adapter<AdapterTienda.viewholdertiendas>  {

    List<Tienda> tiendaList;
    Activity activity;

    public AdapterTienda(List<Tienda> tiendaList, Activity activity) {
        this.tiendaList = tiendaList;
        this.activity = activity;
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
        Glide.with(activity).load(tienda.getUrl()).into(holder.imagen);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DescriptionTienda.class);
                intent.putExtra("id", tienda.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tiendaList.size();
    }

    public class viewholdertiendas extends RecyclerView.ViewHolder {
        TextView tv_nombre, tv_descripcion, tv_dulces;
        ImageView imagen;
        Button boton;
        public viewholdertiendas(@NonNull View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);
            imagen = itemView.findViewById(R.id.imagen);
            boton = itemView.findViewById(R.id.button4);

        }
    }
}
