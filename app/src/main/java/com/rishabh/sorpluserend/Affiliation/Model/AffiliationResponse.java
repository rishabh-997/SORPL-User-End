package com.rishabh.sorpluserend.Affiliation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rishabh.sorpluserend.CompanyData.Model.DataList;

import java.util.List;

public class AffiliationResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("data_list")
    @Expose
    List<AffiliationList> affiliationList;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<AffiliationList> getAffiliationList() {
        return affiliationList;
    }
}
