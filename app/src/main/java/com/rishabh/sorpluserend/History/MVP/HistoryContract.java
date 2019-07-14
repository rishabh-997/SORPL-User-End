package com.rishabh.sorpluserend.History.MVP;


import com.rishabh.sorpluserend.History.Model.HistoryDetailResponse;
import com.rishabh.sorpluserend.History.Model.HistoryResponse;

public class HistoryContract
{
    interface view
    {
        void showToast(String message);

        void showList(HistoryResponse body);

        void showDetails(HistoryDetailResponse body, String id, String comment);
    }
    interface presenter
    {

        void getHistory(String mobile, String client, String company);

        void getDetails(String orderid, String comment);
    }
}
