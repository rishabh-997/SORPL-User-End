package com.example.sorpluserend.CompanyData.MVP;

import com.example.sorpluserend.CompanyData.Model.DataResponse;

public class CompanyContract
{
    interface view
    {

        void showToast(String message);

        void showList(DataResponse body);
    }
    interface presenter
    {

        void getPDF();
    }
}
