package com.example.sorpluserend.HomePage.MVP;

import com.example.sorpluserend.LogIn.MVP.LogInContract;

public class HomePresenter implements HomeContract.presenter
{
    HomeContract.view mvpview;

    public HomePresenter(HomeContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
