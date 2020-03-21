package com.example.ars.Model;

public class SignUpModel {
    private UserModel userModel;
    private UserProfileModel userProfileModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserProfileModel getUserProfileModel() {
        return userProfileModel;
    }

    public void setUserProfileModel(UserProfileModel userProfileModel) {
        this.userProfileModel = userProfileModel;
    }

    public SignUpModel(UserModel userModel, UserProfileModel userProfileModel) {
        this.userModel = userModel;
        this.userProfileModel = userProfileModel;
    }
}
