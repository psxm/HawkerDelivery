package com.colon3.cz2006.hawkerdelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orders;
    private Context context;

    public OrderAdapter(ArrayList<Order> orders, Context context){
        super(context, R.layout.order_layout, orders);
        this.orders = orders;
        this.context = context;
    }
    private static class Holder{
        public TextView idView;
        public TextView nameView;
        public TextView hawkerView;
        public TextView dishView;
        public TextView priceView;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        Holder holder = new Holder();

        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.order_layout, null);
            //fill layout
            TextView id = (TextView) v.findViewById(R.id.order_id);
            TextView name = (TextView) v.findViewById(R.id.customer_id);
            TextView price = (TextView) v.findViewById(R.id.total_price);
            TextView hawkerCenter = (TextView) v.findViewById(R.id.hawker_center);
            TextView dishNumber = (TextView) v.findViewById(R.id.dish_number);
            holder.idView = id;
            holder.nameView = name;
            holder.priceView = price;
            holder.dishView = dishNumber;
            holder.hawkerView = hawkerCenter;
            v.setTag(holder);
        }

        else {holder = (Holder)v.getTag();
        }

        Order o = orders.get(position);
        String s1 = new Integer(o.getCustomerID()).toString();
        holder.nameView.setText("Customer ID: " + s1);
        String s2 = new Double(o.getTotalPrice()).toString();
        holder.priceView.setText("S$ " + s2);
        String s3 = new Integer(o.getDishNum()).toString();
        holder.dishView.setText("Number of dishes: " + s3);
        String s4 = new Integer(o.getHawkerID()).toString();
        holder.hawkerView.setText("Hawker Center ID: " + s4);

        return v;
    }
}
