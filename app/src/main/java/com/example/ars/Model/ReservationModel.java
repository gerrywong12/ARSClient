package com.example.ars.Model;

import com.google.gson.annotations.SerializedName;

public class ReservationModel {

    @SerializedName("user_profile_id")
    int user_profile_id_id;

    @SerializedName("startBookingDateTime")
    String startBookingDateTime;

    @SerializedName("endBookingDateTime")
    String endBookingDateTime;

    @SerializedName("table_type_id")
    int table_type_id;

    @SerializedName("number_of_pax")
    int number_of_pax;

    @SerializedName("reservation_id")
    int reservation_id;

    @SerializedName("reservation_code")
    String reservation_code;

    public int getNumber_of_pax() {
        return number_of_pax;
    }

    public void setNumber_of_pax(int number_of_pax) {
        this.number_of_pax = number_of_pax;
    }

    public void setUser_profile_id_id(int user_profile_id_id) {
        this.user_profile_id_id = user_profile_id_id;
    }

    public void setStartBookingDateTime(String startBookingDateTime) {
        this.startBookingDateTime = startBookingDateTime;
    }

    public void setEndBookingDateTime(String endBookingDateTime) {
        this.endBookingDateTime = endBookingDateTime;
    }

    public void setTable_type_id(int table_type_id) {
        this.table_type_id = table_type_id;
    }

    public int getUser_profile_id_id() {
        return user_profile_id_id;
    }

    public String getStartBookingDateTime() {
        return startBookingDateTime;
    }

    public String getEndBookingDateTime() {
        return endBookingDateTime;
    }

    public int getTable_type_id() {
        return table_type_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getReservation_code() {
        return reservation_code;
    }

    public void setReservation_code(String reservation_code) {
        this.reservation_code = reservation_code;
    }

    public ReservationModel(int user_profile_id){
        this.user_profile_id_id = user_profile_id;
    }

//    public ReservationModel(int user_profile_id_id, String startBookingDateTime, String endBookingDateTime, int table_type_id, int number_of_pax, int reservation_id) {
//        this.user_profile_id_id = user_profile_id_id;
//        this.startBookingDateTime = startBookingDateTime;
//        this.endBookingDateTime = endBookingDateTime;
//        this.table_type_id = table_type_id;
//        this.number_of_pax = number_of_pax;
//        this.reservation_id = reservation_id;
//    }
}
