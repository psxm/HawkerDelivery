package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hippo on 31/03/2016.
 */
public interface DishDAO {
    ArrayList<Dish> getDishByCategories(String Categories);
    ArrayList<Dish> getAllDishes();
    Dish getDishByID(int ID);
}
