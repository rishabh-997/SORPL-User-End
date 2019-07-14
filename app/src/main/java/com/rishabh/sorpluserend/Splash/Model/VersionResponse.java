package com.rishabh.sorpluserend.Splash.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("version")
    @Expose
    String version;
    @SerializedName("compulsory_update")
    @Expose
    Boolean compulsary_update;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }

    public Boolean getCompulsary_update() {
        return compulsary_update;
    }
}
