package com.example.sorpluserend.MyDetails.MVP;

import com.example.sorpluserend.MyDetails.Model.ResponseClient;

public class DetailContract
{
    interface  view
    {

        void showToast(String message);

        void showData(ResponseClient body);
    }
    interface presenter
    {

        void search(String query);
    }
}
