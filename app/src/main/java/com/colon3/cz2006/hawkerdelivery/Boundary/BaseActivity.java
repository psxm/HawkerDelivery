package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.colon3.cz2006.hawkerdelivery.Adapter.NavDrawerAdapter;
import com.colon3.cz2006.hawkerdelivery.Entity.DrawerItem;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hippo on 30/03/2016.
 */
public class BaseActivity extends AppCompatActivity {
    public ListView mDrawerList;
    public DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    public CharSequence mDrawerTittle;
    public CharSequence mTitle;
    public NavDrawerAdapter adapter;
    public List<DrawerItem> dataList;

    protected void onCreateDrawer() {


        mTitle = mDrawerTittle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem("Home", "home"));
        dataList.add(new DrawerItem("Account Settings", "settings"));
        dataList.add(new DrawerItem("Favourites","favourites"));
        dataList.add(new DrawerItem("Logout","logout"));
        adapter = new NavDrawerAdapter(this,R.layout.nav_drawer_row,dataList);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,

                R.string.open_drawer,
                R.string.close_drawer){
            public void onDrawerClosed(View view){
                getSupportActionBar().setTitle(mTitle);
                //invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView){
                mDrawerList.bringToFront();
                mDrawerLayout.requestLayout();
                getSupportActionBar().setTitle(mDrawerTittle);
                //invalidateOptionsMenu();
                //Toast.makeText(drawerView.getContext(),"yolo",Toast.LENGTH_LONG).show();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);



    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle=title;
        getSupportActionBar().setTitle(mTitle);
    }

    public void selectItem(int position){
        Intent intent = null;

        switch (position){
            case 0:
                intent = new Intent(this,HomeActivity.class);
                break;
            case 1:
                intent = new Intent(this,AccSettingsActivity.class);
                break;
            case 2:
                intent = new Intent(this,FavouritesActivity.class);
                break;
            case 3:
                intent = new Intent(this,LoginActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectItem(position);

            //Toast.makeText(parent.getContext(),"yolo",Toast.LENGTH_LONG).show();
        }
    }

}

