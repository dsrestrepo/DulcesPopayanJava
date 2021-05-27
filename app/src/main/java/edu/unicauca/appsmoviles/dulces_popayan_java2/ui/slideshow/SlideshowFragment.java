package edu.unicauca.appsmoviles.dulces_popayan_java2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import edu.unicauca.appsmoviles.dulces_popayan_java2.AdapterTienda;
import edu.unicauca.appsmoviles.dulces_popayan_java2.R;
import edu.unicauca.appsmoviles.dulces_popayan_java2.Tienda;

public class SlideshowFragment extends Fragment {

    DatabaseReference ref;
    ArrayList<Tienda> list;
    RecyclerView rv;
    SearchView search;
    AdapterTienda adapter;
    LinearLayoutManager lm;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        ref = FirebaseDatabase.getInstance().getReference().child("Tiendas");
        // System.out.println(ref);
        rv = root.findViewById(R.id.rv);
        search = root.findViewById(R.id.search);
        lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterTienda(list,getActivity());
        rv.setAdapter(adapter);

        search.setQueryHint("Dulces / candies");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Tienda tienda = dataSnapshot.getValue(Tienda.class);
                        System.out.println(tienda);
                        list.add(tienda);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                buscar(newText);
                return true;
            }
        });

        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
       /*final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;




    }


    private void buscar(String newText) {
        ArrayList<Tienda> milista = new ArrayList<>();
        for (Tienda obj: list){
            if (obj.getDulces().toLowerCase().contains(newText.toLowerCase())){
                milista.add(obj);
            }
        }
        AdapterTienda adapter  = new AdapterTienda(milista,getActivity());
        rv.setAdapter(adapter);
    }
}