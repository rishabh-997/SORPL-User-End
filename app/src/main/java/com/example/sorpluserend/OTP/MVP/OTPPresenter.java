package com.example.sorpluserend.OTP.MVP;

import com.example.sorpluserend.LogIn.Model.LogInResponse;
import com.example.sorpluserend.OTP.Model.OTPResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPPresenter implements OTPContract.presenter
{
    OTPContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public OTPPresenter(OTPContract.view mvpview) {
        this.mvpview = mvpview;
    }


    @Override
    public void verify(String mobile, String otp) {
        clientAPI.verify(mobile, otp).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.enterapp(response.body());
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
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
