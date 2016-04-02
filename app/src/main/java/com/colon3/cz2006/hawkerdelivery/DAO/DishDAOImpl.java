package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Dish;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hippo on 31/03/2016.
 */
public class DishDAOImpl implements DishDAO {
    ArrayList<Dish> dishes;
    public DishDAOImpl(){
        dishes = new ArrayList<Dish>();
        dishes.add(new Dish("Malay","mee_siam","Mee Siam",3.0,2.5f));
        dishes.add(new Dish("Chinese","chicken_rice","Chicken Rice",3.3,4.1f));
        dishes.add(new Dish("Western","steak","Steak",8.0,3.5f));
        dishes.add(new Dish("Malay","nasi_lemak","Nasi Lemak",2.5,3.0f));
        dishes.add(new Dish("Indian","roti_prata","Roti Prata",3.0,3.4f));
        dishes.add(new Dish("Chinese","duck_rice","Duck Rice",3.8,2.0f));
        dishes.add(new Dish("Chinese","beef_noodle_soup","Beef Noodle Soup",4.5,4.5f));


    }

    @Override
    public ArrayList<Dish> getAllDishes() {
        return dishes;
    }

    @Override
    public ArrayList<Dish> getDishByCategories(String Categories) {
        ArrayList<Dish> result = new ArrayList<Dish>();
        for (Dish d : dishes){
            if (d.getCategories().equals(Categories))
                result.add(d);
        }
        return result;
    }
}
