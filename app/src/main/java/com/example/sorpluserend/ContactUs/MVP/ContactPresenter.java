package com.example.sorpluserend.ContactUs.MVP;

import com.example.sorpluserend.ContactUs.Model.ContactResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPresenter implements ContactContract.presenter
{
    ContactContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ContactPresenter(ContactContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getContact() {
        clientAPI.getContact("9935685103").enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.showContact(response.body());
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
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
