package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.colon3.cz2006.hawkerdelivery.R;

public class FavouritesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        super.onCreateDrawer();
        setTitle("Favourites");
    }
}
