package com.rishabh.sorpluserend.HomePage.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnquiryResponse
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean result;

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }
}
