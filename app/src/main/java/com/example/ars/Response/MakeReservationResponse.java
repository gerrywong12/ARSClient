package com.example.ars.Response;

import com.google.gson.annotations.SerializedName;

public class MakeReservationResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MakeReservationResponse(String message) {
        this.message = message;
    }
}
