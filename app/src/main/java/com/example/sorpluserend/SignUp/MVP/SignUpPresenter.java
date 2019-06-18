package com.example.sorpluserend.SignUp.MVP;

public class SignUpPresenter implements SignUpContract.presenter
{
    SignUpContract.view mvpview;

    public SignUpPresenter(SignUpContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
