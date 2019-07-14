package com.rishabh.sorpluserend.HomePage.MVP.Market;

import com.rishabh.sorpluserend.HomePage.Model.MarketResponse;

public class MarketContract
{
    interface view
    {
        void toast(String message);

        void showList(MarketResponse body);


    }
    interface presenter
    {

        void getList();
    }
}
