package com.rishabh.sorpluserend.HomePage.Model;

import com.google.gson.annotations.SerializedName;

public class SpecList
{
    @SerializedName("Heading")
    String heading;
    @SerializedName("Value")
    String value;

    public String getHeading() {
        return heading;
    }

    public String getValue() {
        return value;
    }
}
