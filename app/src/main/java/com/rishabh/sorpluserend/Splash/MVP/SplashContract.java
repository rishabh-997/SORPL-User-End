package com.rishabh.sorpluserend.Splash.MVP;

import com.rishabh.sorpluserend.Splash.Model.VersionResponse;

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
