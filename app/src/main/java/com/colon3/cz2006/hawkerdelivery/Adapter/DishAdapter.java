package com.colon3.cz2006.hawkerdelivery.Adapter;

/**
 * Created by Hippo on 31/03/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;


/**
 * Created by Hippo on 27/03/2016.
 */
public class DishAdapter extends ArrayAdapter<Dish> {
    private ArrayList<Dish> dish;
    private Context context;

    public DishAdapter(ArrayList<Dish> dish, Context context){
        super(context, R.layout.dish_layout,dish);
        this.dish = dish;
        this.context = context;
    }
    private static class Holder{
        public ImageView imgView;
        public TextView nameView;
        public TextView priceView;
        public RatingBar ratingView;
        public TextView ratingTextView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        Holder holder = new Holder();

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.dish_layout, null);
            //fill layout
            TextView name = (TextView) v.findViewById(R.id.dish_name);
            TextView price = (TextView) v.findViewById(R.id.dish_price);
            ImageView img = (ImageView) v.findViewById(R.id.dish_img);
            RatingBar rating = (RatingBar) v.findViewById(R.id.dish_rating);
            TextView ratingText = (TextView) v.findViewById(R.id.dish_rating_text);
            holder.imgView = img;
            holder.nameView = name;
            holder.priceView = price;
            holder.ratingView = rating;
            holder.ratingTextView = ratingText;
            v.setTag(holder);
        }
        else{holder = (Holder)v.getTag();
        }
        Dish c = dish.get(position);
        String imgString = "drawable/"+c.getImgSource();
        int imageRes = v.getContext().getApplicationContext().getResources()
                .getIdentifier(imgString, null, v.getContext().getApplicationContext().getPackageName());

        holder.imgView.setImageResource(imageRes);
        holder.nameView.setText(c.getName());
        String s = new Double(c.getPrice()).toString();
        holder.priceView.setText("S$" + s);
        holder.ratingView.setRating(c.getRating());
        String s2 = Float.toString(c.getRating());
        holder.ratingTextView.setText("("+s2+")");
        return v;
    }
}
