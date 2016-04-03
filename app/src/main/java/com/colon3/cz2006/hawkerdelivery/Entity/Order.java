package com.colon3.cz2006.hawkerdelivery.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yeong How on 3/31/2016.
 */
public class Order {
    private int orderID;
    private int customerID;
    private ArrayList<Dish> dishes;
    private int hawkerID;
    private Date dateTime;
    private double totalPrice;
    private int deliveryId;
    private String status;

    public Order() {

    }

    public Order(int id, int customerID, int hawkerID, ArrayList<Dish> dishes, double totalPrice) {
        this.orderID = id;
        this.customerID = customerID;
        this.hawkerID = hawkerID;
        this.dateTime = new Date();
        this.dishes = dishes;
        this.totalPrice = totalPrice;
        this.status = "Yes";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public int getHawkerID() {
        return hawkerID;
    }

    public void setHawkerID(int hawkerID) {
        this.hawkerID = hawkerID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Override
    public String toString() {
        return "Order [id=" + orderID + ", customerId=" + customerID + ", hawkerCenterId=" + hawkerID
                + "status=" + status + "totalPrice=" + totalPrice + "dateTime=" + dateTime + "]";
    }
}
