package com.colon3.cz2006.hawkerdelivery.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michelle on 4/7/2016.
 */
public class HawkerCentre implements Parcelable {
    private String name;
    private String address;
    private double distanceFromUser;
    private String imgSource;

    public HawkerCentre(String name, String address, double distanceFromUser, String imgSource) {
        this.name = name;
        this.address = address;
        this.distanceFromUser = distanceFromUser;
        this.imgSource = imgSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(double distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Flatten this object in to a Parcel.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(distanceFromUser);
        dest.writeString(imgSource);
    }

    // We reconstruct the object reading from the Parcel data
    public HawkerCentre(Parcel in) {
        this.name = in.readString();
        this.address = in.readString();
        this.distanceFromUser = in.readDouble();
        this.imgSource = in.readString();
    }

    // We need to add a Creator
    public static final Parcelable.Creator<HawkerCentre> CREATOR 
            = new Parcelable.Creator<HawkerCentre>() {

        @Override
        public HawkerCentre createFromParcel(Parcel in) {
            return new HawkerCentre(in);
        }

        @Override
        public HawkerCentre[] newArray(int size) {
            return new HawkerCentre[size];
        }
    };
}
