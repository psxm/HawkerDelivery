package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.colon3.cz2006.hawkerdelivery.Adapter.DishAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.DishController;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class ViewByFoodCatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ListView list;
    public ArrayList<Dish> dishArrayList = new ArrayList<Dish>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_by_food_cat);
        setTitle("Food Category");
        String[] foodCat = new String[] {"All Categories","Chinese","Western","Malay","Indian"};

        Spinner spinner = (Spinner)findViewById(R.id.food_cat_spinner);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item_dark,foodCat);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        list = (ListView)findViewById(R.id.list);
        DishController controller = new DishController();

        if(position == 0){
            dishArrayList = controller.sortByRating();
            DishAdapter adapter = new DishAdapter(dishArrayList, this);
            list.setAdapter(adapter);
        }
        else{

            String item = parent.getItemAtPosition(position).toString();
            dishArrayList = controller.sortByRatingByCat(item);
            DishAdapter adapter = new DishAdapter(dishArrayList, this);
            list.setAdapter(adapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish d = dishArrayList.get(position);
                Intent i = new Intent(getApplicationContext(),OrderActivity.class);
                i.putExtra("dish_object",d);
                startActivity(i);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
