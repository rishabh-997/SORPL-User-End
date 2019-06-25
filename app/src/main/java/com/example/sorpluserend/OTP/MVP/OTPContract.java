package com.example.sorpluserend.OTP.MVP;

import com.example.sorpluserend.LogIn.Model.LogInResponse;
import com.example.sorpluserend.OTP.Model.OTPResponse;

public class OTPContract
{
    interface view
    {
        void showToast(String message);

        void enterapp(OTPResponse body);
    }
    interface presenter
    {
        void verify(String mobile,String otp);
    }
}
