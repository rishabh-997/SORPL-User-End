package com.rishabh.sorpluserend.HomePage.MVP;

public class HomePresenter implements HomeContract.presenter
{
    HomeContract.view mvpview;

    public HomePresenter(HomeContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
