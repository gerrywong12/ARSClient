package com.example.ars.Response;

public class UserDataResponse {
    String user_name;
    int user_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserDataResponse(String user_name, int user_id) {
        this.user_name = user_name;
        this.user_id = user_id;
    }
}
