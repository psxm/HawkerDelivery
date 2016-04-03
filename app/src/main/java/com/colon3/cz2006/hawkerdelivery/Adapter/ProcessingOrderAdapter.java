package com.colon3.cz2006.hawkerdelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.colon3.cz2006.hawkerdelivery.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProcessingOrderAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orders;
    private Context context;

    public ProcessingOrderAdapter(ArrayList<Order> orders, Context context){
        super(context, R.layout.processing_order_layout, orders);
        this.orders = orders;
        this.context = context;
    }
    private static class Holder{
        public TextView idView;
        public TextView addressView;
        public TextView hawkerView;
        public TextView remainTimeView;
        public TextView priceView;
        public TextView dateView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        Holder holder = new Holder();

        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.processing_order_layout, null);
            //fill layout
            TextView id = (TextView) v.findViewById(R.id.processing_order_id);
            TextView address = (TextView) v.findViewById(R.id.customer_address);
            TextView price = (TextView) v.findViewById(R.id.processing_total_price);
            TextView hawkerCenter = (TextView) v.findViewById(R.id.processing_hawker_center);
            TextView remainTime = (TextView) v.findViewById(R.id.remaining_prepare_time);
            TextView dateTime = (TextView) v.findViewById(R.id.order_dateTime);
            holder.idView = id;
            holder.addressView = address;
            holder.priceView = price;
            holder.remainTimeView = remainTime;
            holder.hawkerView = hawkerCenter;
            holder.dateView = dateTime;
            v.setTag(holder);
        }

        else {holder = (Holder)v.getTag();
        }

        //OrderDAOImpl orderDAO = new OrderDAOImpl();
        Order o = orders.get(position);

        String id = Integer.toString(o.getOrderID());
        holder.idView.setText("Order ID " + id);

        String price = new Double(o.getTotalPrice()).toString();
        holder.priceView.setText("S$ " + price);

        //String address = orderDAO.getAddressFromOrder(o);
        //holder.addressView.setText("Location: " + address);

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a, EEE");
        String date = format.format(o.getDateTime());
        holder.dateView.setText("Ordered at " + date);

        String hawkerCenter = new Integer(o.getHawkerID()).toString();
        holder.hawkerView.setText("Hawker " + hawkerCenter);

        String remainTime = "Time remain: to be implemented";
        holder.remainTimeView.setText(remainTime);

        return v;
    }
}
