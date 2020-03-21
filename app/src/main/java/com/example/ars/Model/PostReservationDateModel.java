package com.example.ars.Model;

import com.google.gson.annotations.SerializedName;

public class PostReservationDateModel {
    @SerializedName("table_type_id")
    private int table_type_id;
    @SerializedName("bookingDate")
    private String bookingDate;

    public int getTable_type_id() {
        return table_type_id;
    }

    public void setTable_type_id(int table_type_id) {
        this.table_type_id = table_type_id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public PostReservationDateModel(int table_type_id, String bookingDate) {
        this.table_type_id = table_type_id;
        this.bookingDate = bookingDate;
    }
}
