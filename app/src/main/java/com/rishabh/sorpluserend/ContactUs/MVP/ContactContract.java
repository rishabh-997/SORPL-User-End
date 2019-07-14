package com.rishabh.sorpluserend.ContactUs.MVP;

import com.rishabh.sorpluserend.ContactUs.Model.ContactResponse;

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
