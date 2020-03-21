package com.example.ars.Interface;

import com.example.ars.Model.LoginModel;
import com.example.ars.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("login/")
    Call<LoginResponse> Login(@Body LoginModel loginModel);
}
