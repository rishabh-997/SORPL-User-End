package com.example.sorpluserend.Splash.MVP;

import com.example.sorpluserend.Splash.Model.VersionResponse;

public class SplashContract
{
    interface view
    {

        void showToast(String message);

        void checkVersion(VersionResponse body);
    }
    interface presenter
    {

        void getVersion();
    }
}
