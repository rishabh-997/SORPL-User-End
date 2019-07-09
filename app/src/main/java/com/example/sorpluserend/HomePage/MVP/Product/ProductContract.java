package com.example.sorpluserend.HomePage.MVP.Product;

import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.HomePage.Model.UnitResponse;

public class ProductContract
{
    interface view
    {

        void showtaost(String message);

        void showCompanies(Comapny_response body);

        void showSubCategories(SubCat_response body);

        void showdata(Product_Response body);

        void showSpecs(SpecResponse body);

        void updateMyCart(String successfully_added_to_cart);

        void setList(UnitResponse body);
    }
    interface presenter
    {
        void getCompany();
        void getSubCategory(String company);
        void getList(String subcat,String company);
        void getSpecs(String name);
        void addCart(String pid,String mobile,String size, String unit,String nvm);

        void getUnit();
    }
}
