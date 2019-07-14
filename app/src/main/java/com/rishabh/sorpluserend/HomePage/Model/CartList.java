package com.rishabh.sorpluserend.HomePage.Model;

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

    @SerializedName("price")
    String price;

    @SerializedName("sub_category")
    String subcat;

    @SerializedName("cart_id")
    String cartid;

    @SerializedName("Size")
    String size;
    @SerializedName("Unit")
    String unit;

    public String getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getCartid() {
        return cartid;
    }

    public String getSubcat() {
        return subcat;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
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
