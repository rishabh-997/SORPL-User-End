package com.example.sorpluserend.Help.MVP;

import com.example.sorpluserend.Help.Model.HelpResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpPresenter implements HelpContract.presenter
{
    HelpContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public HelpPresenter(HelpContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getHelp() {
        clientAPI.getHelp("9935685103").enqueue(new Callback<HelpResponse>() {
            @Override
            public void onResponse(Call<HelpResponse> call, Response<HelpResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.fillView(response.body());
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
            public void onFailure(Call<HelpResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
