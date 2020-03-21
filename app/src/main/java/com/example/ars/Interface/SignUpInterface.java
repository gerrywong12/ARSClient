package com.example.ars.Interface;

import com.example.ars.Model.SignUpModel;
import com.example.ars.Response.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpInterface {
    @POST("register/")
    Call<SignUpResponse> Register(@Body SignUpModel signUpModel);
}
