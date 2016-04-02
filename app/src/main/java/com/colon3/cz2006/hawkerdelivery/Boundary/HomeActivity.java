package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.colon3.cz2006.hawkerdelivery.R;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();
        setTitle("Home");

        Button viewByHawker = (Button)findViewById(R.id.hawker_btn);
        viewByHawker.setOnClickListener(this);
        Button viewByFoodCat = (Button)findViewById(R.id.food_btn);
        viewByFoodCat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()) {
            case R.id.hawker_btn:
                i = new Intent(HomeActivity.this, ViewByHawkerActivity.class);
                break;
            case R.id.food_btn:
                i = new Intent(HomeActivity.this, ViewByFoodCatActivity.class);
                break;
            default:
                break;
        }
        startActivity(i);
    }
}
