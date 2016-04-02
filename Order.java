package com.colon3.cz2006.hawkerdelivery.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yeong How on 3/31/2016.
 */
public class Order {
    private int orderID;
    private int customerID;
    private ArrayList<Integer> dishID;
    private int hawkerID;
    private Date dateTime;
    private double totalPrice;

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    private int deliveryID;

    public Order(int id, int customerID, int hawkerID, int dishNum, double totalPrice) {
        this.orderID = id;
        this.customerID = customerID;
        this.hawkerID = hawkerID;
        this.dateTime = new Date();
        this.dishNum = dishNum;
        this.totalPrice = totalPrice;
        this.status = "Yes";
    }
    public int getDishNum() {
        return dishNum;
    }

    public void setDishNum(int dishNum) {
        this.dishNum = dishNum;
    }

    private int dishNum;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public Order() {
        this.orderID = 1;
        this.dateTime = new Date();
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

    public ArrayList<Integer> getDishID() {
        return dishID;
    }

    public void setDishID(ArrayList<Integer> dishID) {
        this.dishID = dishID;
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

    @Override
    public String toString() {
        return "Order [id=" + orderID + ", customerId=" + customerID + ", hawkerCenterId=" + hawkerID
                + "status=" + status + "totalPrice=" + totalPrice + "dateTime=" + dateTime + "]";
    }
}
