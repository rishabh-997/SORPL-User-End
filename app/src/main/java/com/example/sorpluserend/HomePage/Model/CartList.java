package com.example.sorpluserend.HomePage.Model;

import com.google.gson.annotations.SerializedName;

public class CartList {
    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("id")
    String id;

    @SerializedName("image")
    String image_url;

    public CartList(String name, String description, String id, String image_url, String sub_category, String size, String unit, String cost, String total_cost, String cgst, String sgst, String total_cost_tax) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.image_url = image_url;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }
}
