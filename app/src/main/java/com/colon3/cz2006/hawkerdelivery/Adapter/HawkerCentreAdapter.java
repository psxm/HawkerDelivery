package com.colon3.cz2006.hawkerdelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.HawkerCentre;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

/**
 * Created by Michelle on 4/7/2016.
 */
public class HawkerCentreAdapter extends ArrayAdapter<HawkerCentre> {
    private ArrayList<HawkerCentre> hawkerCentres;
    private Context context;

    public HawkerCentreAdapter(ArrayList<HawkerCentre> hawkerCentres, Context context) {
        super(context, R.layout.hawker_layout, hawkerCentres);
        this.hawkerCentres = hawkerCentres;
        this.context = context;
    }

    private static class Holder{
        public ImageView imgView;
        public TextView nameView;
        public TextView addressView;
        public TextView distanceView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        Holder holder = new Holder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.hawker_layout, null);
            //fill layout
            ImageView img = (ImageView) v.findViewById(R.id.hawker_img);
            TextView name = (TextView) v.findViewById(R.id.hawker_name);
            TextView address = (TextView) v.findViewById(R.id.hawker_address);
            TextView distance = (TextView) v.findViewById(R.id.hawker_distance);
            holder.imgView = img;
            holder.nameView = name;
            holder.addressView = address;
            holder.distanceView = distance;
            v.setTag(holder);
        }
        else{holder = (Holder)v.getTag();
        }
        HawkerCentre hc = hawkerCentres.get(position);
        String imgString = "drawable/"+hc.getImgSource();
        int imageRes = v.getContext().getApplicationContext().getResources()
                .getIdentifier(imgString, null, v.getContext().getApplicationContext().getPackageName());

        holder.imgView.setImageResource(imageRes);
        holder.nameView.setText(hc.getName());
        holder.addressView.setText(hc.getAddress());
        holder.distanceView.setText(hc.getDistanceFromUser()+"km");
        return v;
    }

}
