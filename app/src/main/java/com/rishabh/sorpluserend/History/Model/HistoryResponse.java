package com.rishabh.sorpluserend.History.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("order_list")
    @Expose
    List<HistoryList> historyList;

    public HistoryResponse(Boolean success, String message, List<HistoryList> historyList) {
        this.success = success;
        this.message = message;
        this.historyList = historyList;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<HistoryList> getHistoryList() {
        return historyList;
    }
}
