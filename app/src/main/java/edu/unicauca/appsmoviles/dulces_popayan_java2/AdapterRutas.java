package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterRutas extends RecyclerView.Adapter<AdapterRutas.viewholderrutas>  {

    List<Rutas> rutasList;
    Activity activity;

    public AdapterRutas(List<Rutas> rutasList, Activity activity) {
        this.rutasList = rutasList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterRutas.viewholderrutas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate with row
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_rutas, parent, false);
        AdapterRutas.viewholderrutas holder = new AdapterRutas.viewholderrutas(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRutas.viewholderrutas holder, int position) {
        Rutas ruta = rutasList.get(position);

        holder.tv_nombre.setText(ruta.getNombre());
        //Glide.with(activity).load(tienda.getUrl()).into(holder.imagen);
        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MapsActivity.class);
                intent.putExtra("id", ruta.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rutasList.size();
    }

    public class viewholderrutas extends RecyclerView.ViewHolder {
        TextView tv_nombre;
        //ImageView imagen;
        Button boton;
        public viewholderrutas(@NonNull View itemView) {
            super(itemView);

            tv_nombre = itemView.findViewById(R.id.tv_nombre_ruta);
            // imagen = itemView.findViewById(R.id.imagen);
            boton = itemView.findViewById(R.id.ver_ruta);

        }
    }
}
