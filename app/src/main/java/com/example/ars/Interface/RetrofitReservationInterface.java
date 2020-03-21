package com.example.ars.Interface;

import com.example.ars.Model.ReservationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitReservationInterface {
    @POST("getCurrentReservations/")
    Call<List<ReservationModel>> PostData(@Body ReservationModel user_profile_id_dataset);
//    Call<RequestBody> getCurrentReservations(@Part MultipartBody.Part part, @Part("somedata")RequestBody requestBody);
}
