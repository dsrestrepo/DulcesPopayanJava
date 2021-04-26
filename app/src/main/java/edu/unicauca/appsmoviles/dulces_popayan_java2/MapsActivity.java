package edu.unicauca.appsmoviles.dulces_popayan_java2;

import android.Manifest;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationRequest locationRequest;

    Bundle rutas;

    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        maps(googleMap);
    }

    public void maps(GoogleMap googleMap) {
        rutas = getIntent().getExtras();
        Integer selector = rutas.getInt("ruta");
        miUbicacion();
        if (selector == 1) {

            //popayan - parque caldas
            LatLng popayan = new LatLng(2.441941, -76.606308);
            mMap.addMarker(new MarkerOptions().position(popayan).title("Ciudad de Popayán").snippet("Lugar de las dulces rutas"));

            //morro
            LatLng morro = new LatLng(2.44407, -76.60112);
            mMap.addMarker(new MarkerOptions().position(morro).title("El Morro").snippet("Mira un atardecer").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            //caucana de dulces
            LatLng caucana = new LatLng(2.4432516, -76.6059773);
            mMap.addMarker(new MarkerOptions().position(caucana).title("Caucana de dulces tipicos").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            //doña chepa
            LatLng chepa = new LatLng(2.4435641, -76.6039177);
            mMap.addMarker(new MarkerOptions().position(chepa).title("Aplanchados doña chepa").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(popayan, 15));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(2.441941, -76.606308), new LatLng(2.442470, -76.606555), new LatLng(2.443301, -76.606278), new LatLng(2.443019, -76.605388), new LatLng(2.443584, -76.605176), new LatLng(2.443782, -76.605098), new LatLng(2.443806, -76.605086), new LatLng(2.443786, -76.604890), new LatLng(2.443808, -76.604846), new LatLng(2.443616, -76.604189), new LatLng(2.443368, -76.603355), new LatLng(2.444216, -76.603054), new LatLng(2.443918, -76.602209), new LatLng(2.443630, -76.601296), new LatLng(2.444077, -76.601203), new LatLng(2.44407, -76.60112))
                    .width(4)
                    .color(Color.RED));
        }

        else if(selector == 2){

            //morro
            LatLng morro = new LatLng(2.44407, -76.60112);
            mMap.addMarker(new MarkerOptions().position(morro).title("El Morro").snippet("Mira un atardecer").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            //parque caldas
            LatLng popayan = new LatLng(2.441941, -76.606308);
            mMap.addMarker(new MarkerOptions().position(popayan).title("Ciudad de Popayán").snippet("Lugar de las dulces rutas").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            //colonial postres
            LatLng colonial = new LatLng(2.442674, -76.601308);
            mMap.addMarker(new MarkerOptions().position(colonial).title("Colonial Postres").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            //El reposo
            LatLng reposo = new LatLng(2.441729, -76.601977);
            mMap.addMarker(new MarkerOptions().position(reposo).title("Helado, Fruta y Cafe").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(popayan, 15));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(2.44407, -76.60112),new LatLng(2.444077, -76.601203), new LatLng(2.443599, -76.601305), new LatLng(2.441914, -76.601909),new LatLng(2.440225, -76.602497), new LatLng(2.440327, -76.602870), new LatLng(2.440349, -76.602937), new LatLng(2.440487, -76.603367), new LatLng(2.440736, -76.604274), new LatLng(2.441596, -76.603968), new LatLng(2.441864, -76.604823), new LatLng(2.442148, -76.605708),new LatLng(2.441941, -76.606308))
                    .width(4)
                    .color(Color.RED));

        }

        else {

            //popayan
            LatLng popayan = new LatLng(2.441941, -76.606308);
            mMap.addMarker(new MarkerOptions().position(popayan).title("Ciudad de Popayán").snippet("Lugar de las dulces rutas"));

            //la fresa
            LatLng fresa = new LatLng(2.442228, -76.608934);
            mMap.addMarker(new MarkerOptions().position(fresa).title("La Fresa").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            //helados de paila
            LatLng paila = new LatLng(2.440024, -76.609459);
            mMap.addMarker(new MarkerOptions().position(paila).title("Helados de paila").snippet("mmm").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(popayan, 15));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(2.441941, -76.606308),new LatLng(2.441559, -76.606913), new LatLng(2.441862, -76.607787),new LatLng(2.442760, -76.607455), new LatLng(2.443050, -76.608326), new LatLng(2.443326, -76.609180), new LatLng(2.442418, -76.609506), new LatLng(2.442136, -76.608660), new LatLng(2.441266, -76.608993), new LatLng(2.440535, -76.609273), new LatLng(2.439623, -76.609610), new LatLng(2.439365, -76.608714), new LatLng(2.439069, -76.607836), new LatLng(2.439935, -76.607518), new LatLng(2.439622, -76.606635), new LatLng(2.440395, -76.606331), new LatLng(2.441291, -76.606030), new LatLng(2.441941, -76.606308))
                    .width(4)
                    .color(Color.RED));
        }



    }


    private void agregarMarcador(double lat, double lng) {
        LatLng coordenas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenas, 15);
        if (marcador != null) marcador.remove();
        {
            marcador = mMap.addMarker(new MarkerOptions()
                    .position(coordenas)
                    .title("aqui estas")
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