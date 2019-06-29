package com.example.sorpluserend.HomePage.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarketResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean result;

    @SerializedName("user_list")
    @Expose
    private
    List<MarketList> list;

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }

    public List<MarketList> getList() {
        return list;
    }
}
