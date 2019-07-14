package com.rishabh.sorpluserend.CompanyData.MVP;

import com.rishabh.sorpluserend.CompanyData.Model.DataResponse;

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
