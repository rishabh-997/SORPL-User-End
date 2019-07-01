package com.example.sorpluserend.HomePage.MVP.Enquiry;

import com.example.sorpluserend.HomePage.Model.MarketResponse;

public class EnquiryContract
{
    interface view
    {
        void toast(String message);


        void sent(String message);
    }
    interface presenter
    {

        void sendMail(String name, String mobile, String company, String email, String message);
    }
}
