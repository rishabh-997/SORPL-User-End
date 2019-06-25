package com.example.sorpluserend.OTP.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("access_token")
    @Expose
    String access_token;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }
}
