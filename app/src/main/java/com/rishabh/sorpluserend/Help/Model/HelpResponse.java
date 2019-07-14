package com.rishabh.sorpluserend.Help.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HelpResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("help")
    @Expose
    String help;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getHelp() {
        return help;
    }
}
