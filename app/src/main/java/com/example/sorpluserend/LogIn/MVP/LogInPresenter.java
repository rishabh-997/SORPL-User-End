package com.example.sorpluserend.LogIn.MVP;

import android.support.v7.view.menu.MenuView;

import com.example.sorpluserend.LogIn.Model.LogInResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter implements LogInContract.presenter
{
    LogInContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public LogInPresenter(LogInContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void dologin(String mobile, String fcm) {
        clientAPI.login(mobile,fcm).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Otp Sent"))
                    {
                        mvpview.showToast(response.body().getMessage());
                        mvpview.OTPPage();
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                {
                    mvpview.showToast(response.message());
                }

            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                    mvpview.showToast(t.getMessage());
            }
        });
    }
}
