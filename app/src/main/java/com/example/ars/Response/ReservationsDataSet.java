package com.example.ars.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDataSet   {

    @SerializedName("startBookingDateTime")
    private String startBookingDateTime;

    @SerializedName("endBookingDateTime")
    private String endBookingDateTime;


    public String getStartBookingDateTime() {
        return startBookingDateTime;
    }

    public void setStartBookingDateTime(String startBookingDateTime) {
        this.startBookingDateTime = startBookingDateTime;
    }

    public String getEndBookingDateTime() {
        return endBookingDateTime;
    }

    public void setEndBookingDateTime(String endBookingDateTime) {
        this.endBookingDateTime = endBookingDateTime;
    }

    public ReservationsDataSet(String startBookingDateTime, String endBookingDateTime) {
        this.startBookingDateTime = startBookingDateTime;
        this.endBookingDateTime = endBookingDateTime;
    }

}
