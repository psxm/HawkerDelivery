package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.colon3.cz2006.hawkerdelivery.Adapter.ProcessingOrderAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.OrderController;
import com.colon3.cz2006.hawkerdelivery.DAO.OrderDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class ListProcessingOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ListView list;
    private ArrayList<Order> orders = new ArrayList<>();
    OrderDAOImpl orderDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_processing_order);
        setTitle("Processing Orders");
        String[] listOrderCat = new String[] {"Hawker Center", "Time"};
        Spinner spinner = (Spinner)findViewById(R.id.list_processing_order_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_dark,listOrderCat);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        orderDAO = new OrderDAOImpl(this);
        orders = orderDAO.getAllOrdersByStatus("No");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list = (ListView)findViewById(R.id.processing_order_list);
        OrderController controller = new OrderController();
        //orders = orderDAO.getAllOrdersByStatus("No");
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
        ProcessingOrderAdapter adapter = new ProcessingOrderAdapter(orders, this);
        list.setAdapter(adapter);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
