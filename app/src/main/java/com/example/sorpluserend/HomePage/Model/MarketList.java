package com.example.sorpluserend.HomePage.Model;

import com.google.gson.annotations.SerializedName;

public class MarketList
{
    @SerializedName("Name")
    String name;
    @SerializedName("Price_INR")
    String inr;
    @SerializedName("Price_USD")
    String usd;
    @SerializedName("Change_tag")
    String tag;
    @SerializedName("Change_Percentage")
    String percent;
    @SerializedName("Description")
    String desc;
    @SerializedName("images")
    String image_url;

    public String getImage_url() {
        return image_url;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getInr() {
        return inr;
    }

    public String getUsd() {
        return usd;
    }

    public String getTag() {
        return tag;
    }

    public String getPercent() {
        return percent;
    }
}
