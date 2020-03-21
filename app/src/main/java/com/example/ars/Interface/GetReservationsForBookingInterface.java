package com.example.ars.Interface;

import com.example.ars.Model.PostReservationDateModel;
import com.example.ars.Response.GetReservationsForBookingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GetReservationsForBookingInterface {
    @POST("getReservationForBooking/")
    Call<GetReservationsForBookingResponse> GetReservations(@Body PostReservationDateModel postReservationDateModel);
}
