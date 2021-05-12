package edu.unicauca.appsmoviles.dulces_popayan_java2.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.unicauca.appsmoviles.dulces_popayan_java2.AdapterRutas;
import edu.unicauca.appsmoviles.dulces_popayan_java2.AdapterTienda;
import edu.unicauca.appsmoviles.dulces_popayan_java2.MapsActivity;
import edu.unicauca.appsmoviles.dulces_popayan_java2.R;
import edu.unicauca.appsmoviles.dulces_popayan_java2.Rutas;
import edu.unicauca.appsmoviles.dulces_popayan_java2.Tienda;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

   // Button btn1,btn2,btn3;
    DatabaseReference ref;
    ArrayList<Rutas> list;
    RecyclerView rv;
    AdapterRutas adapter;
    LinearLayoutManager lm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        ref = FirebaseDatabase.getInstance().getReference().child("Rutas");
        // System.out.println(ref);
        rv = root.findViewById(R.id.rv_rutas);
        lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterRutas(list,getActivity());
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Rutas ruta = dataSnapshot.getValue(Rutas.class);
                        //System.out.println(ruta);
                        list.add(ruta);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

       /* final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


/*        btn1 = root.findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("ruta", 1);
                startActivity(intent);
            }
        });

        btn2 = root.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("ruta", 2);
                startActivity(intent);
            }
        });

        btn3 = root.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("ruta", 3);
                startActivity(intent);
            }
        });*/

        return root;
    }
}