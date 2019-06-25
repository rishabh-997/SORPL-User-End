package com.example.sorpluserend.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{

    private static final String PREF_NAME = "welcome";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String MOBILE = "mobile";
    private static final String FCM = "fcm";
    int Private_mode=0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPref(Context context)
    {
        this.context=context;
        pref=context.getSharedPreferences(PREF_NAME,Private_mode);
        editor=pref.edit();
    }

    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, "");
    }
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getMobile()
    {
        return pref.getString(MOBILE,"");
    }
    public void setMobile(String mobile)
    {
        editor.putString(MOBILE,mobile);
        editor.commit();
    }

    public String getFCM()
    {
        return pref.getString(FCM,"");
    }
    public void setFCM(String fcm)
    {
        editor.putString(FCM,fcm);
        editor.commit();
    }

}
