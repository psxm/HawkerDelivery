package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.colon3.cz2006.hawkerdelivery.R;

public class DeliveryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        setTitle("Delivery Team");

        Button listOrders = (Button)findViewById(R.id.list_order_btn);
        listOrders.setOnClickListener(this);
        Button listProcessingOrders = (Button)findViewById(R.id.list_processing_order_btn);
        listProcessingOrders.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.list_order_btn:
                i = new Intent(DeliveryActivity.this, ListOrderActivity.class);
                break;
            case R.id.list_processing_order_btn:
                i = new Intent(DeliveryActivity.this, ListProcessingOrderActivity.class);
                break;
            default:
                break;
        }
        startActivity(i);
    }
}
