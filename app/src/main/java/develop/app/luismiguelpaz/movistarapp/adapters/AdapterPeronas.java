package develop.app.luismiguelpaz.movistarapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import develop.app.luismiguelpaz.movistarapp.R;
import develop.app.luismiguelpaz.movistarapp.model.DataBaseRow;


public class AdapterPeronas extends BaseAdapter {



    Activity activity;
    ArrayList<DataBaseRow> arrayListPersona;

    Button buttonVerUbicacion;
    TextView textViewNombrePersona;
    ImageView imageViewPersona;
    View.OnClickListener listener;

    public AdapterPeronas(Activity activity, View.OnClickListener listener) {
        this.activity = activity;

        this.listener=listener;

    }

    @Override
    public int getCount() {
        return arrayListPersona.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListPersona.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li= activity.getLayoutInflater();

        View persona= li.inflate(R.layout.adapter_persona,parent,false);


        return persona;
    }
}
