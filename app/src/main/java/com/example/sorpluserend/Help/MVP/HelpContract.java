package com.example.sorpluserend.Help.MVP;

import com.example.sorpluserend.Help.Model.HelpResponse;

public class HelpContract
{
    interface  view
    {

        void showToast(String message);

        void fillView(HelpResponse body);
    }
    interface presenter
    {

        void getHelp();
    }
}
