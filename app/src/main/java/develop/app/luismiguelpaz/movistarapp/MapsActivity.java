package develop.app.luismiguelpaz.movistarapp;

import android.content.res.AssetManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import develop.app.luismiguelpaz.movistarapp.model.DataBaseRow;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<DataBaseRow> lista;



    private LatLng point;
    private String desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if(getIntent()!=null&&getIntent().getExtras()!=null&&!getIntent().getExtras().isEmpty()){
            desc=getIntent().getExtras().getString("Text");
            Double lat=getIntent().getExtras().getDouble("Lat");
            Double lng=getIntent().getExtras().getDouble("Long");
            point=new LatLng(lat,lng);
        }

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


        AssetManager am = this.getAssets();
        try {
            InputStream is = am.open("apartamentos-casa.txt");

            BufferedReader in=new BufferedReader(new InputStreamReader(is));

            String line=in.readLine();
            int contador=0;
            while(line!=null){

                DataBaseRow dataBaseRow=new DataBaseRow();
                StringTokenizer skt=new StringTokenizer(line,",");

                if(contador<=4){

                    dataBaseRow.setCoordenadaCaja(new LatLng(Double.parseDouble(skt.nextToken()),Double.parseDouble(skt.nextToken())));
                }else{
                    dataBaseRow.setCoordenadaUsuario(new LatLng(Double.parseDouble(skt.nextToken()),Double.parseDouble(skt.nextToken())));
                }
                lista.add(dataBaseRow);
                line=in.readLine();
                contador++;

            }
        }catch (Exception e){

        }

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

            if(lista.get(i).getCoordenadaUsuario()!=null){
                mMap.addMarker(new MarkerOptions()
                        .position(lista.get(i).getCoordenadaUsuario()));
                if(lat==0){

                    lat=lista.get(i).getCoordenadaUsuario().latitude;
                    lng=lista.get(i).getCoordenadaUsuario().longitude;
                }else{
                    lat+=lista.get(i).getCoordenadaUsuario().latitude;
                    lng+=lista.get(i).getCoordenadaUsuario().longitude;

                }
            }else{
                CircleOptions circleOptions = new CircleOptions()
                        .center(lista.get(i).getCoordenadaCaja())
                        .radius(150); // In meters

                Circle circle = mMap.addCircle(circleOptions);

            }

        }
        lat/=lista.size();
        lng/=lista.size();

        if(point!=null){
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
            mMap.addMarker(new MarkerOptions()
                    .position(point).icon(bitmapDescriptor).title(desc));
            mMap.setMinZoomPreference(6);

        }else{
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));
            mMap.setMinZoomPreference(6);
        }



    }
}
