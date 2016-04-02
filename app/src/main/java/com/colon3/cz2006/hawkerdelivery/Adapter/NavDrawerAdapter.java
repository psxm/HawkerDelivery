package com.colon3.cz2006.hawkerdelivery.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.DrawerItem;
import com.colon3.cz2006.hawkerdelivery.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hippo on 28/03/2016.
 */
public class NavDrawerAdapter extends ArrayAdapter<DrawerItem> {
    private Context context;
    private List<DrawerItem> drawerItemList;
    int layoutResID;

    public NavDrawerAdapter(Context context, int resource, List<DrawerItem> objects){
        super(context,resource,objects);
        this.context = context;
        this.drawerItemList = objects;
        this.layoutResID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerItemHolder holder;
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            holder = new DrawerItemHolder();
            v = inflater.inflate(layoutResID,parent,false);
            holder.title = (TextView)v.findViewById(R.id.drawer_title);
            holder.icon = (ImageView)v.findViewById(R.id.drawer_icon);
            v.setTag(holder);
        }
        else    holder = (DrawerItemHolder) v.getTag();

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
        String iconString = "drawable/"+dItem.getIcon_source();
        int iconRes = v.getContext().getApplicationContext().getResources().getIdentifier(iconString,null,v.getContext().getApplicationContext().getPackageName());

        holder.icon.setImageResource(iconRes);
        holder.title.setText(dItem.getTitle());
        return v;
    }
    private static class DrawerItemHolder{
        TextView title;
        ImageView icon;
    }
}
