package com.sw.sgtu;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.request.ParaderoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    /*private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;*/

    Intent intent;

    ApiService apiService;
    private final String TAG = RegistroActivity.class.getSimpleName();

    Toast toast;

    List<ParaderoResponse> paraderoResponseList = new ArrayList<>();

    private int puntos_seleccionados = 0;

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
        mMap.getUiSettings().setMapToolbarEnabled(false);

        LatLng centro = new LatLng(-12.0566208, -77.0798912);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(centro)   //Centramos el mapa
                .zoom(15)         //Establecemos el zoom
                .bearing(45)      //Establecemos la orientación con el noreste arriba
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mMap.animateCamera(camUpd3);
        //Antut(googleMap);
        getListParaderosLatLon(googleMap);
    }

    public void getListParaderosLatLon(final GoogleMap googleMap) {
        apiService = ApiAdapter.createServiceSecondAPI(ApiService.class);
        Call<List<ParaderoResponse>> call = apiService.getListParaderosLatLon();
        call.enqueue(new Callback<List<ParaderoResponse>>() {
            @Override
            public void onResponse(Call<List<ParaderoResponse>> call, Response<List<ParaderoResponse>> response) {
                if(response.isSuccessful()){
                    paraderoResponseList = response.body();
                    colocarPuntos(googleMap, paraderoResponseList);
                }
            }

            @Override
            public void onFailure(Call<List<ParaderoResponse>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }

    public void colocarPuntos(GoogleMap googleMap, List<ParaderoResponse> paraderos){
        mMap = googleMap;
        for (ParaderoResponse paradero: paraderos){
            LatLng punto = new LatLng(paradero.getLatitud(), paradero.getLongitud());
            System.out.println("punto " + punto);
            System.out.println("Direccion " + paradero.getDireccion());
            mMap.addMarker(new MarkerOptions().position(punto)
                    .title(paradero.getDireccion())
                    .icon(BitmapDescriptorFactory.defaultMarker()))
                    .setTag(0);
        }
        mMap.setOnMarkerClickListener(this);
    }

    /*public void Antut(GoogleMap googleMap){
        mMap = googleMap;

        final LatLng punto1 = new LatLng(-12.049462, -77.078151);
        final LatLng punto2 = new LatLng(-12.0599671, -77.0787708);
        final LatLng punto3 = new LatLng(-12.0468762, -77.0766725);
        final LatLng punto4 = new LatLng(-12.0445503, -77.0767113);
        final LatLng punto5 = new LatLng(-12.0620836, -77.078774);
        final LatLng punto6 = new LatLng(-12.0643071, -77.0782408);
        final LatLng punto7 = new LatLng(-12.0570522, -77.0797949);

        mMap.addMarker(new MarkerOptions().position(punto1).title("Paradero Av. Colonial")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto2).title("Paradero Av. Venezuela")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto3).title("Paradero Av. Argentina")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto4).title("Paradero Av. Maquinarias")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto5).title("Paradero Ferre")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto6).title("Paradero Calle Tulipanes")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);

        mMap.addMarker(new MarkerOptions().position(punto7).title("Paradero Amezaga")
                .icon(BitmapDescriptorFactory.defaultMarker())).setTag(0);



        mMap.setOnMarkerClickListener(this);

    }*/

    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.

        if (clickCount != null) {
            clickCount = clickCount + 1;
            if(clickCount == 2){
                clickCount = 0;
                marker.setIcon(BitmapDescriptorFactory.defaultMarker());
                marker.setTag(clickCount);
                puntos_seleccionados--;
            }else {
                if(puntos_seleccionados != 2){
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    marker.setTag(clickCount);
                    Toast.makeText(this,marker.getTitle() + " ha sido seleccionado",
                            Toast.LENGTH_SHORT).show();
                    puntos_seleccionados++;
                }
            }
            System.out.println("SE SELECCIONO " + puntos_seleccionados + " PUNTOS!");
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    public void redirectPosiblesRutas(View view) {
        if(puntos_seleccionados == 2){
            intent = new Intent(MapsActivity.this, ResultadosActivity.class);
            startActivity(intent);
        }else {
            toast = Toast.makeText(this, "Necesita seleccionar 2 paraderos", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
