package com.example.ars.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReservationsForBookingResponse {
    @SerializedName("numberOfTable")
    private int numberOfTable;

    @SerializedName("reservations")
    List<ReservationsDataSet> reservationsDataSet;

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public List<ReservationsDataSet> getReservationsDataSet() {
        return reservationsDataSet;
    }

    public void setReservationsDataSet(List<ReservationsDataSet> reservationsDataSet) {
        this.reservationsDataSet = reservationsDataSet;
    }

    public GetReservationsForBookingResponse(int numberOfTable, List<ReservationsDataSet> reservationsDataSet) {
        this.numberOfTable = numberOfTable;
        this.reservationsDataSet = reservationsDataSet;
    }
}
