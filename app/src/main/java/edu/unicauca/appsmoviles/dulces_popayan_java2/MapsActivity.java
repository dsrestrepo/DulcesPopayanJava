package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationRequest locationRequest;

    Bundle rutas;
    DatabaseReference ref;
    DatabaseReference ref2;
    Activity activity;
    ArrayList<Punto> list;
    ArrayList<Tienda> listTienda;
    Punto punto_aux;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        activity = this;



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        list = new ArrayList<Punto>();
        listTienda = new ArrayList<Tienda>();

        rutas = getIntent().getExtras();
        String selector = rutas.getString("id");

        // database
        ref = FirebaseDatabase.getInstance().getReference().child("Rutas").child(selector).child(selector);
        ref2 = FirebaseDatabase.getInstance().getReference().child("Tiendas");
/*
        System.out.println(ref);
        // get database data
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Punto punto = dataSnapshot.getValue(Punto.class);
                        list.add(punto);
                        myCallback.onCallback(list);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Punto punto = dataSnapshot.getValue(Punto.class);
                        list.add(punto);
                        //System.out.println(list.get(0).getLongitud());
                    }
                    ref2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    Tienda tienda = dataSnapshot.getValue(Tienda.class);
                                    listTienda.add(tienda);
                                }
                                // show Roue
                                mostrar_ruta(googleMap, list, listTienda);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        ref.addValueEventListener(postListener);

    }

    private void mostrar_ruta(GoogleMap googleMap, ArrayList<Punto> list, ArrayList<Tienda> listTienda) {

        //location
        miUbicacion();

        //popayan - parque caldas
        LatLng popayan = new LatLng(2.441941, -76.606308);
        mMap.addMarker(new MarkerOptions().position(popayan).title("Ciudad de Popayán").snippet("Lugar de las dulces rutas"));

        //morro
        LatLng morro = new LatLng(2.44407, -76.60112);
        mMap.addMarker(new MarkerOptions().position(morro).title("El Morro").snippet("Mira un atardecer").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        for (int i = 0; i < listTienda.size() ; i++) {

            LatLng location = new LatLng(listTienda.get(i).getLatitud(), listTienda.get(i).getLongitud());
            mMap.addMarker(new MarkerOptions().position(location).title(listTienda.get(i).getNombre()).snippet(listTienda.get(i).getDulces()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


        }




        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(popayan, 15));

        for (int i = 0; i < list.size() ; i++) {
            if (i == (list.size() -1)){
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(
                                new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud()))
                        .width(4)
                        .color(Color.RED));

            }
            else {
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        //.add(new LatLng(2.441941, -76.606308), new LatLng(2.442470, -76.606555), new LatLng(2.443301, -76.606278), new LatLng(2.443019, -76.605388), new LatLng(2.443584, -76.605176), new LatLng(2.443782, -76.605098), new LatLng(2.443806, -76.605086), new LatLng(2.443786, -76.604890), new LatLng(2.443808, -76.604846), new LatLng(2.443616, -76.604189), new LatLng(2.443368, -76.603355), new LatLng(2.444216, -76.603054), new LatLng(2.443918, -76.602209), new LatLng(2.443630, -76.601296), new LatLng(2.444077, -76.601203), new LatLng(2.44407, -76.60112))
                        .add(
                                new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud()), new LatLng(list.get(i + 1).getLatitud(), list.get(i + 1).getLongitud()))
                        .width(4)
                        .color(Color.RED));
            }
        }




    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenas, 15);
        if (marcador != null) marcador.remove();
        {
            marcador = mMap.addMarker(new MarkerOptions()
                    .position(coordenas)
                    .title("aquí estás")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            mMap.animateCamera(miUbicacion);
        }
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {

            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {


        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    };

    private void miUbicacion() {
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Context context = getApplicationContext();
            CharSequence text = "Error";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0,locListener);
    }



}