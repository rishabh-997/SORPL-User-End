package com.rishabh.sorpluserend.Affiliation.Model;

import com.google.gson.annotations.SerializedName;

public class AffiliationList
{
    @SerializedName("Name")
    String Name;

    @SerializedName("Url")
    String Url;

    @SerializedName("description")
    String description;

    @SerializedName("date")
    String date;

    public String getName() {
        return Name;
    }

    public String getUrl() {
        return Url;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
