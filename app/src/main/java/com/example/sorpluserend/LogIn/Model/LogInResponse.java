package com.example.sorpluserend.LogIn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;

    public LogInResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
