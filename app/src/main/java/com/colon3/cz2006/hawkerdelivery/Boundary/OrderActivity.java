package com.colon3.cz2006.hawkerdelivery.Boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.R;

import org.w3c.dom.Text;

public class OrderActivity extends AppCompatActivity {
    private int quantity=0;
    private double price;
    private Button decrement_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Order");
        Intent i = getIntent();
        Dish dishObject = (Dish)i.getParcelableExtra("dish_object");
        ImageView imgView = (ImageView)findViewById(R.id.dish_img_order);
        TextView nameView = (TextView)findViewById(R.id.dish_name_order);
        RatingBar ratingView = (RatingBar)findViewById(R.id.dish_rating_order);
        decrement_btn = (Button)findViewById(R.id.decrement_btn);

        nameView.setText(dishObject.getName());
        ratingView.setRating(dishObject.getRating());
        price = dishObject.getPrice();
        String priceString = new Double(price).toString();

        String imgString = "drawable/"+dishObject.getImgSource();
        int imageRes = getApplicationContext().getResources()
                .getIdentifier(imgString, null, getApplicationContext().getPackageName());

        imgView.setImageResource(imageRes);
        if(quantity==0)
            decrement_btn.setEnabled(false);


    }
    public void decrement(View view){

        quantity--;
        if(quantity==0)
            decrement_btn.setEnabled(false);
        displayQuantityAndPrice(quantity);


    }
    public void increment(View view){
        quantity++;
        decrement_btn.setEnabled(true);
        displayQuantityAndPrice(quantity);
    }
    public void displayQuantityAndPrice(int quantity){
        TextView quantityView = (TextView)findViewById(R.id.order_quantity);
        quantityView.setText(String.valueOf(quantity));
        TextView priceView = (TextView)findViewById(R.id.dish_price_order);
        String priceString = new Double(price*quantity).toString();
        priceView.setText("S$" + priceString);
    }


}
