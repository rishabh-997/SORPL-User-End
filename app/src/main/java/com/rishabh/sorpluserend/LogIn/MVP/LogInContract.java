package com.rishabh.sorpluserend.LogIn.MVP;

public class LogInContract
{
    interface view
    {
        void showToast(String message);
        void OTPPage();
    }
    interface presenter
    {
        void dologin(String mobile,String fcm);
    }
}
