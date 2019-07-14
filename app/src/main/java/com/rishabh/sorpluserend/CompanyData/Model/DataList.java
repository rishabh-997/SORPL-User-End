package com.rishabh.sorpluserend.CompanyData.Model;

import com.google.gson.annotations.SerializedName;

public class DataList
{
    @SerializedName("Name")
    String name;

    @SerializedName("Url")
    String url;

    @SerializedName("description")
    String description;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
