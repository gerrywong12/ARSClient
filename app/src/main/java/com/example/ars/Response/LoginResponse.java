package com.example.ars.Response;

import android.service.autofill.UserData;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    String message;
    @SerializedName("user_data")
    UserDataResponse user_data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDataResponse getUserDataResponse() {
        return user_data;
    }

    public void setUserDataResponse(UserDataResponse user_data) {
        this.user_data = user_data;
    }

    public LoginResponse(String message, UserDataResponse user_data) {
        this.message = message;
        this.user_data = user_data;
    }
}
