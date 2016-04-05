package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.colon3.cz2006.hawkerdelivery.Adapter.OrderAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.OrderController;
import com.colon3.cz2006.hawkerdelivery.DAO.OrderDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;
import java.util.Date;

public class ListOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private ListView list;
    private ArrayList<Order> orders = new ArrayList<>();
    private OrderDAOImpl orderDAO;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        setTitle("Available Orders");
        String[] listOrderCat = new String[] {"Hawker Center", "Time"};
        Spinner spinner = (Spinner)findViewById(R.id.list_order_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_dark,listOrderCat);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        Button button = (Button) findViewById(R.id.processing_btn);
        button.setOnClickListener(this);

        orderDAO = new OrderDAOImpl(this);

/*
        Order o = new Order();
        o.setHawkerID(15);
        o.setStatus("Yes");
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish("Malay","mee_goreng","Mee Siam",3.0,6.2f));
        o.setDishes(dishes);
        o.setDeliveryId(2);
        o.setCustomerID(1);
        o.setDateTime(new Date());
        o.setTotalPrice(11.2);
        o.setOrderID(orderDAO.getLastId()+1);
        orderDAO.addOrder(o);
*/

        orders = orderDAO.getAllOrdersByStatus("Yes");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list = (ListView)findViewById(R.id.order_list);
        OrderController controller = new OrderController();
        switch (position) {
            case 0:
                orders = controller.sortByHawkerCenter(orders);
                break;
            case 1:
                orders = controller.sortByTime(orders);
                break;
            default:
                orders = controller.sortByTime(orders);
        }
        adapter = new OrderAdapter(orders, this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, int position, long id) {
                final Order o = orders.get(position);
                CharSequence choices[] = new CharSequence[]{"Yes", "No"};
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirm to add this order");
                builder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on choices[which]
                        if (which == 0) {
                            o.setStatus("No");
                            orderDAO.updateOrder(o);
                            String item = "Successfully added";
                            Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();

                            // restart activity to refresh list view
                            Context context = view.getContext();
                            ((ListOrderActivity) context).finish();
                            Intent intent = new Intent(context, ListOrderActivity.class);
                            context.startActivity(intent);

                            // refresh data
                            // orders = orderDAO.getAllOrdersByStatus("Yes");
                            // adapter.notifyDataSetChanged();
                            //OrderAdapter orderAdapter = new OrderAdapter(orders, parent.getContext());
                            //list.setAdapter(orderAdapter);
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View view) {
        Intent i;
        if (view.getId() == R.id.processing_btn) {
            i = new Intent(ListOrderActivity.this, ListProcessingOrderActivity.class);
            startActivity(i);
        }
    }
}
