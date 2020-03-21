package com.example.ars.Response;

import com.google.gson.annotations.SerializedName;

public class FoodResponse {
    @SerializedName("foodName")
    private String foodName;

    @SerializedName("foodDesc")
    private String foodDesc;

    @SerializedName("foodCode")
    private String foodCode;

    @SerializedName("price")
    private String price;

    @SerializedName("foodImg")
    private String foodImg;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public FoodResponse(String foodName, String foodDesc, String foodCode, String price, String foodImg) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodCode = foodCode;
        this.price = price;
        this.foodImg = foodImg;
    }
}
