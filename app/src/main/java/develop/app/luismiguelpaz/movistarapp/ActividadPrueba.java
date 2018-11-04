package develop.app.luismiguelpaz.movistarapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ActividadPrueba extends AppCompatActivity {


    Button butEncontrar;
    Button butVerEnMapa;
    TextView textViewResultado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_prueba);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



// Define a listener that responds to location updates

        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions


                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                final Location lastKnown=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                butVerEnMapa.setEnabled(true);
                System.out.println("k");

                textViewResultado.setText("Lat: "+lastKnown.getLatitude()+", Long:"+lastKnown.getLongitude());



            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        textViewResultado=findViewById(R.id.tv_resultado);
        butVerEnMapa=findViewById(R.id.but_verEnMapa);
        butVerEnMapa.setEnabled(false);
        butVerEnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geocoder geocoder = new Geocoder(ActividadPrueba.this, Locale.getDefault());

                List<Address> addresses = null;

                if (ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions


                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                final Location lastKnown=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                try {
                    addresses = geocoder.getFromLocation(
                            lastKnown.getLatitude(),
                            lastKnown.getLongitude(),
                            1);

                    textViewResultado.setText(textViewResultado.getText()+"\n"+addresses.get(0).getAddressLine(0)+"\n Departamento: "+addresses.get(0).getAdminArea()+"\n Municipio: "+addresses.get(0).getSubAdminArea());


                    Toast.makeText(ActividadPrueba.this,textViewResultado.getText()+"\n"+addresses.get(0).getAddressLine(0)+"\n Departamento: "+addresses.get(0).getAdminArea()+"\n Municipio: "+addresses.get(0).getSubAdminArea(),Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mainIntent=new Intent(ActividadPrueba.this,MapsActivity.class).putExtra("Lat",lastKnown.getLatitude()).putExtra("Long",lastKnown.getLongitude()).putExtra("Text",textViewResultado.getText().toString());
                            startActivity(mainIntent);
                            finish();
                        }
                    },5000);

                    finish();

                } catch (IOException ioException) {
                    ioException.printStackTrace();


                } catch (IllegalArgumentException illegalArgumentException) {
                    illegalArgumentException.printStackTrace();

                }
            }
        });
        butEncontrar = findViewById(R.id.but_encontrarUbicacion);
        butEncontrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActividadPrueba.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions

                    ActivityCompat.requestPermissions(ActividadPrueba.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},11);

                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60, 0, locationListener);



            }
        });


    }





}
