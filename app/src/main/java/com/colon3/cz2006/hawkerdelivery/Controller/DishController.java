package com.colon3.cz2006.hawkerdelivery.Controller;

import com.colon3.cz2006.hawkerdelivery.DAO.DishDAO;
import com.colon3.cz2006.hawkerdelivery.DAO.DishDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Hippo on 31/03/2016.
 */
public class DishController {
    DishDAO dishDAO = new DishDAOImpl();
    public DishController(){

    }
    public ArrayList<Dish> sortByRating (){
        ArrayList<Dish> result = dishDAO.getAllDishes();
        Collections.sort(result, new Comparator<Dish>() {
            @Override
            public int compare(Dish lhs, Dish rhs) {
                if(lhs.getRating()>rhs.getRating())
                    return -1;
                else if (lhs.getRating()<rhs.getRating())
                    return 1;
                else return 0;
            }
        });
        return result;
    }
    public ArrayList<Dish> sortByRatingByCat (String categories){
        ArrayList<Dish> result = dishDAO.getDishByCategories(categories);
        Collections.sort(result, new Comparator<Dish>() {
            @Override
            public int compare(Dish lhs, Dish rhs) {
                if(lhs.getRating()>rhs.getRating())
                    return -1;
                else if (lhs.getRating()<rhs.getRating())
                    return 1;
                else return 0;
            }
        });
        return result;
    }
    public ArrayList<Dish> getFavourites(ArrayList<Integer> favourites){
        ArrayList<Dish> result = new ArrayList<>();
        for (Integer i : favourites){
            result.add(dishDAO.getDishByID(i));
        }
        return result;
    }
}
