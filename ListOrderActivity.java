package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.colon3.cz2006.hawkerdelivery.Adapter.OrderAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.OrderController;
import com.colon3.cz2006.hawkerdelivery.DAO.OrderDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class ListOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView list;
    private ArrayList<Order> orders = new ArrayList<>();
    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        setTitle("Available Orders");
        String[] listOrderCat = new String[] {"Hawker Center", "Time"};
        Spinner spinner = (Spinner)findViewById(R.id.list_order_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_item_dark,listOrderCat);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list = (ListView)findViewById(R.id.order_list);
        OrderController controller = new OrderController();
        orders = orderDAO.getAllOrdersByStatus("Yes");
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
        OrderAdapter adapter = new OrderAdapter(orders, this);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
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
                            o.setDeliveryID(1);
                            o.setTotalPrice(99.99);
                            String item = "Successfully added";
                            Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();

                            // refresh data
                            orders = orderDAO.getAllOrdersByStatus("Yes");
                            OrderAdapter orderAdapter = new OrderAdapter(orders, parent.getContext());
                            list.setAdapter(orderAdapter);
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
