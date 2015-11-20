package com.orochi.utfpr.levaeu.Activitys;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.GPS.GPSTracker;
import com.orochi.utfpr.levaeu.Motorista;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Sapo;
import com.orochi.utfpr.levaeu.Sessao;

import java.util.List;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Carona carona;
    private Sapo sapo;
    private Motorista motorista;
    private List<Carona> caronas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        caronas = (List<Carona>) getIntent().getSerializableExtra("caronas");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        for(Carona carona : caronas){
            LatLng markerCaronaOrigem = new LatLng(carona.getOrigem().getCoordenada().getLatitude(), carona.getOrigem().getCoordenada().getLongitude());
            mMap.addMarker(new MarkerOptions().position(markerCaronaOrigem).title("Carona Origem"));

            LatLng markerCaronaDestino = new LatLng(carona.getDestino().getCoordenada().getLatitude(),carona.getDestino().getCoordenada().getLongitude());
            mMap.addMarker(new MarkerOptions().position(markerCaronaDestino).title("Carona Destino"));

        }

        GPSTracker gps = new GPSTracker(MapaActivity.this);
            LatLng me = new LatLng(gps.getLatitude(), gps.getLongitude());
            mMap.addMarker(new MarkerOptions().position(me).title("Eu :)").icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(me));


    }
}
