package com.example.ars.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("email")
    String email;

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserModel(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
