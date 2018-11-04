package develop.app.luismiguelpaz.movistarapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import develop.app.luismiguelpaz.movistarapp.model.DataBaseRow;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<DataBaseRow> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lista=new ArrayList<>();
        DataBaseRow nr=new DataBaseRow();
        nr.setCoordenadaCaja(new LatLng(4.44213, -75.19708));
        lista.add(nr);
        DataBaseRow nr1=new DataBaseRow();
        nr1.setCoordenadaCaja(new LatLng( 4.43702 , -75.21407));
        lista.add(nr1);
        DataBaseRow nr2=new DataBaseRow();
        nr2.setCoordenadaCaja(new LatLng( 4.45701, -75.24625));
        lista.add(nr2);

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

        double lat=0;
        double lng=0;

        for(int i=0; i<lista.size();i++){

            CircleOptions circleOptions = new CircleOptions()
                    .center(lista.get(i).getCoordenadaCaja())
                    .radius(120); // In meters

            Circle circle = mMap.addCircle(circleOptions);

            lat+=lista.get(i).getCoordenadaCaja().latitude;
            lng+=lista.get(i).getCoordenadaCaja().longitude;

        }
        lat/=lista.size();
        lng/=lista.size();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));

        mMap.setMinZoomPreference(5);



    }
}
