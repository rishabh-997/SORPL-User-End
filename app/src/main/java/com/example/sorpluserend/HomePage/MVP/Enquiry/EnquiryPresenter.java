package com.example.sorpluserend.HomePage.MVP.Enquiry;

import com.example.sorpluserend.HomePage.Model.EnquiryResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnquiryPresenter implements EnquiryContract.presenter
{
    EnquiryContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public EnquiryPresenter(EnquiryContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void sendMail(String name, String mobile, String company, String email, String message) {
        clientAPI.sendEmail(name, mobile, email,company, message).enqueue(new Callback<EnquiryResponse>() {
            @Override
            public void onResponse(Call<EnquiryResponse> call, Response<EnquiryResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Enquiry Sent"))
                    {
                        mvpview.sent(response.body().getMessage());
                    }
                    else
                    {
                        mvpview.toast(response.body().getMessage());
                    }
                }
                else
                {
                    mvpview.toast(response.message());
                }
            }

            @Override
            public void onFailure(Call<EnquiryResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
