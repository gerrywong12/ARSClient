package com.example.ars.Interface;

import com.example.ars.Model.SignUpModel;
import com.example.ars.Response.FoodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface FoodInterface {
    @GET("getFood/")
    Call<List<FoodResponse>> getAllFood();
}
