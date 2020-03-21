package com.example.ars.Interface;

import com.example.ars.Model.LoginModel;
import com.example.ars.Model.MakeReservationModel;
import com.example.ars.Response.LoginResponse;
import com.example.ars.Response.MakeReservationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MakeReservationInterface {
    @POST("makeReservation/")
    Call<MakeReservationResponse> Reserve(@Body MakeReservationModel makeReservationModel);
}
