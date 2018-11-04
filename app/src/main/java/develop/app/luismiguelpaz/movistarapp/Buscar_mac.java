package develop.app.luismiguelpaz.movistarapp;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import develop.app.luismiguelpaz.movistarapp.model.WEBUtilDomi;

public class Buscar_mac extends AppCompatActivity {

    EditText et_mac;
    Button btn_mac;
    TextView txt_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_mac);

        et_mac = findViewById(R.id.et_mac);
        btn_mac = findViewById(R.id.btn_buscar);
        txt_resultado = findViewById(R.id.txt_resultado);

        btn_mac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String json = null;
                        try {
                            json = WEBUtilDomi.GETrequest("https://api.mylnikov.org/geolocation/wifi?v=1.1&data=open&bssid=" + et_mac.getText().toString());
                            Gson gson = new Gson();
                            final GeoLocation location = gson.fromJson(json, GeoLocation.class);
                            System.out.println(location.getData().getLat());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //00:0C:42:1F:65:E9
                                    txt_resultado.setText("Lat: "+location.getData().getLat()+", Lng : "+location.getData().getLon());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();


            }
        });

    }

    public class GeoLocation{

        private Data data = new Data();

        public GeoLocation(){

        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data{
            double lat;
            double lon;

            public Data(){

            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }
        }
    }

}
