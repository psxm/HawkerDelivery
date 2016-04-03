package com.colon3.cz2006.hawkerdelivery.Controller;

import com.colon3.cz2006.hawkerdelivery.Entity.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Yeong How on 3/31/2016.
 */
public class OrderController {

    public ArrayList<Order> sortByHawkerCenter(ArrayList<Order> orders) {
        ArrayList<Order> result = orders;
        Collections.sort(result, new Comparator<Order>() {
            @Override
            public int compare(Order lhs, Order rhs) {
                if(lhs.getHawkerID()<rhs.getHawkerID())
                    return -1;
                else if (lhs.getHawkerID()>rhs.getHawkerID())
                    return 1;
                else return 0;
            }
        });
        return result;
    }

    public ArrayList<Order> sortByTime(ArrayList<Order> orders) {
        ArrayList<Order> result = orders;
        Collections.sort(result, new Comparator<Order>() {
            @Override
            public int compare(Order lhs, Order rhs) {
                if(lhs.getDateTime().compareTo(rhs.getDateTime()) < 0)
                    return -1;
                else if (lhs.getDateTime().compareTo(rhs.getDateTime()) > 0)
                    return 1;
                else return 0;
            }
        });
        return result;
    }
}
