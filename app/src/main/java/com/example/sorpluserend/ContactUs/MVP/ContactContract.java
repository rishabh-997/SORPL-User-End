package com.example.sorpluserend.ContactUs.MVP;

import com.example.sorpluserend.ContactUs.Model.ContactResponse;

public class ContactContract
{
    interface view
    {
        void toast(String message);
        void showContact(ContactResponse body);
    }
    interface presenter
    {
        void getContact();
    }
}
