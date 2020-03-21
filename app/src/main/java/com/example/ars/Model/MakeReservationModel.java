package com.example.ars.Model;

import com.google.gson.annotations.SerializedName;

public class MakeReservationModel {
    @SerializedName("table_type_id")
    private int table_type_id;

    @SerializedName("endBookingDateTime")
    private String endBookingDateTime;

    @SerializedName("startBookingDateTime")
    private String startBookingDateTime;

    @SerializedName("user_profile_id_id")
    private int user_profile_id;

    @SerializedName("number_of_pax")
    private int number_of_pax;

    public int getTable_type_id() {
        return table_type_id;
    }

    public void setTable_type_id(int table_type_id) {
        this.table_type_id = table_type_id;
    }

    public String getEndBookingDateTime() {
        return endBookingDateTime;
    }

    public void setEndBookingDateTime(String endBookingDateTime) {
        this.endBookingDateTime = endBookingDateTime;
    }

    public String getStartBookingDateTime() {
        return startBookingDateTime;
    }

    public void setStartBookingDateTime(String startBookingDateTime) {
        this.startBookingDateTime = startBookingDateTime;
    }

    public int getUser_profile_id() {
        return user_profile_id;
    }

    public void setUser_profile_id(int user_profile_id) {
        this.user_profile_id = user_profile_id;
    }

    public int getNumber_of_pax() {
        return number_of_pax;
    }

    public void setNumber_of_pax(int number_of_pax) {
        this.number_of_pax = number_of_pax;
    }

    public MakeReservationModel(int table_type_id, String endBookingDateTime, String startBookingDateTime, int user_profile_id, int number_of_pax) {
        this.table_type_id = table_type_id;
        this.endBookingDateTime = endBookingDateTime;
        this.startBookingDateTime = startBookingDateTime;
        this.user_profile_id = user_profile_id;
        this.number_of_pax = number_of_pax;
    }
}
