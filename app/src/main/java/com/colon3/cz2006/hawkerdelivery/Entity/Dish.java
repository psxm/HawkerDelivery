package com.colon3.cz2006.hawkerdelivery.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hippo on 30/03/2016.
 */
public class Dish implements Parcelable{
    private String name;
    private float rating;
    private String imgSource;
    private String categories;
    private double price;

    public Dish(String categories, String imgSource, String name, double price, float rating) {
        
        this.categories = categories;
        this.imgSource = imgSource;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(rating);
        dest.writeString(imgSource);
        dest.writeString(categories);
        dest.writeDouble(price);
    }
    public Dish(Parcel in){
        this.name=in.readString();
        this.rating=in.readFloat();
        this.imgSource=in.readString();
        this.categories=in.readString();
        this.price=in.readDouble();
    }
    public static final Parcelable.Creator<Dish> CREATOR
            =new Parcelable.Creator<Dish>(){
        public Dish createFromParcel(Parcel in){
            return new Dish(in);
        }
        public Dish[] newArray(int size){
            return new Dish[size];
        }
    };
}
