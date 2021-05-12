package edu.unicauca.appsmoviles.dulces_popayan_java2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DescriptionTienda extends AppCompatActivity {

    Bundle intentinfo;
    DatabaseReference ref;
    TextView nombre_tienda, descripcion_tienda, dulces_tienda;
    ImageView imagen_tienda;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_tienda);

        activity = this;

        nombre_tienda = findViewById(R.id.nombre_tienda);
        imagen_tienda = findViewById(R.id.imagen_tienda);
        descripcion_tienda = findViewById(R.id.descripcion_tienda);
        dulces_tienda = findViewById(R.id.dulces_tienda);
        intentinfo = getIntent().getExtras();
        String idTienda = intentinfo.getString("id");

        // database
        ref = FirebaseDatabase.getInstance().getReference().child("Tiendas").child(idTienda);
        //System.out.println(ref);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Tienda tienda2 = snapshot.getValue(Tienda.class);
                nombre_tienda.setText(tienda2.getNombre());
                descripcion_tienda.setText(tienda2.getDescripcion());
                dulces_tienda.setText(tienda2.getDulces());
                Glide.with(activity).load(tienda2.getUrl()).into(imagen_tienda);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }
}