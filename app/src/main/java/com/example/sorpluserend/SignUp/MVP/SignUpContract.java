package com.example.sorpluserend.SignUp.MVP;

public class SignUpContract
{
    interface view
    {

        void toast(String message);

        void success();
    }
    interface presenter
    {

        void createUser(String name, String mobile, String email, String company, String client);
    }
}
