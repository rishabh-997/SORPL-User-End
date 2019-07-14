package com.rishabh.sorpluserend.Utilities;

public class Utils
{
    private Utils(){}

    public static String BaseUrl="http://139.59.92.206:8000/";


    public static ClientAPI getClientAPI()
    {
        return RetrofitClient.getClient(BaseUrl).create(ClientAPI.class);
    }
}
