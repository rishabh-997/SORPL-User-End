package com.example.sorpluserend.HomePage.MVP.Market;

import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.MarketResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_response;

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
