package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.colon3.cz2006.hawkerdelivery.Adapter.HawkerCentreAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.HawkerCentreController;
import com.colon3.cz2006.hawkerdelivery.Entity.HawkerCentre;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class ViewByHawkerActivity extends AppCompatActivity {
    private ListView list;
    public ArrayList<HawkerCentre> hcArrayList = new ArrayList<HawkerCentre>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_by_hawker);
        setTitle("Hawker Centres");

        viewList();
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void viewList() {
        list = (ListView) findViewById(R.id.hawker_centre_listview);
        HawkerCentreController controller = new HawkerCentreController();

        hcArrayList = controller.allHawkerCentres();
        HawkerCentreAdapter adapter = new HawkerCentreAdapter(hcArrayList, this);
        list.setAdapter(adapter);
    }
}
