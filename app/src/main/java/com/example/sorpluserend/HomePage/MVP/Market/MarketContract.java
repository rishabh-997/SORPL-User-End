package com.example.sorpluserend.HomePage.MVP.Market;

import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.SubCat_response;

public class MarketContract
{
    interface view
    {
        void toast(String message);

        void showList(CartResponse body);
        void showCompanies(Comapny_response body);

        void showSubCategories(SubCat_response body);

    }
    interface presenter
    {
        void getCompany();
        void getSubCategory(String company);
        void getMarket(String client,String company, String subcat);
    }
}
