package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Adapter.DishAdapter;
import com.colon3.cz2006.hawkerdelivery.Controller.DishController;
import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;

public class FavouritesActivity extends BaseActivity  {
    private ListView list;
    private ArrayList<Dish> dishArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        super.onCreateDrawer();
        setTitle("Favourites");
        list = (ListView)findViewById(R.id.list);
        dishArrayList= new ArrayList<Dish>();
        DishController controller = new DishController();
        if(LoginActivity.accountSession instanceof CustomerAccount)
            dishArrayList = controller.getFavourites(((CustomerAccount) LoginActivity.accountSession).getFavourites());
        DishAdapter adapter = new DishAdapter(dishArrayList, this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish d = dishArrayList.get(position);
                Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                i.putExtra("dish_object", d);
                startActivity(i);
            }
        });


    }
}
