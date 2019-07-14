package com.rishabh.sorpluserend.OTP.MVP;

import com.rishabh.sorpluserend.OTP.Model.OTPResponse;

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
