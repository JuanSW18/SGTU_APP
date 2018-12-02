package com.sw.sgtu;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        /*LatLng sanmarcos = new LatLng(-12.0558094, -77.0882352);
        mMap.addMarker(new MarkerOptions().position(sanmarcos).title("UNMSM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanmarcos));*/

        LatLng centro = new LatLng(-12.0566208, -77.0798912);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(centro)   //Centramos el mapa en Madrid
                .zoom(15)         //Establecemos el zoom en 19
                .bearing(45)      //Establecemos la orientación con el noreste arriba//Bajamos el punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mMap.animateCamera(camUpd3);
        Antut(googleMap);
    }

    public void Antut(GoogleMap googleMap){
        mMap = googleMap;

        final LatLng punto1 = new LatLng(-12.049462, -77.078151);
        final LatLng punto2 = new LatLng(-12.0599671, -77.0787708);
        final LatLng punto3 = new LatLng(-12.0468762, -77.0766725);
        final LatLng punto4 = new LatLng(-12.0445503, -77.0767113);
        final LatLng punto5 = new LatLng(-12.0620836, -77.078774);
        final LatLng punto6 = new LatLng(-12.0643071, -77.0782408);
        final LatLng punto7 = new LatLng(-12.0570522, -77.0797949);

        mMap.addMarker(new MarkerOptions().position(punto1).title("Paradero Av. Colonial")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto2).title("Paradero Av. Venezuela")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto3).title("Paradero Av. Argentina")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto4).title("Paradero Av. Maquinarias")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto5).title("Paradero Ferre")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto6).title("Paradero Calle Tulipanes")
                .icon(BitmapDescriptorFactory.defaultMarker()));

        mMap.addMarker(new MarkerOptions().position(punto7).title("Paradero Amezaga")
                .icon(BitmapDescriptorFactory.defaultMarker()));

    }
}
