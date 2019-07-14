package com.rishabh.sorpluserend.HomePage.Model;

import com.google.gson.annotations.SerializedName;

public class Comapany_list
{
    @SerializedName("Company")
    String comapanyfull;

    @SerializedName("ShorForm")
    String companyshort;

    public String getComapanyfull() {
        return comapanyfull;
    }

    public String getCompanyshort() {
        return companyshort;
    }
}
