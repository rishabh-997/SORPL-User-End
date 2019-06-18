package com.example.sorpluserend.LogIn.MVP;

public class LogInPresenter implements LogInContract.presenter
{
    LogInContract.view mvpview;

    public LogInPresenter(LogInContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
