package com.example.ars.Model;

import com.google.gson.annotations.SerializedName;

public class UserProfileModel {
    @SerializedName("userRole")
    int userRole;
    @SerializedName("phoneNo")
    Long phoneNo;
    @SerializedName("age")
    int age;
    @SerializedName("birthDate")
    String birthDate;
    @SerializedName("gender")
    int gender;

    public UserProfileModel(int userRole, Long phoneNo, int age, String birthDate, int gender) {
        this.userRole = userRole;
        this.phoneNo = phoneNo;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
