package com.rishabh.sorpluserend.HomePage.MVP.Market;

import com.rishabh.sorpluserend.HomePage.Model.MarketResponse;
import com.rishabh.sorpluserend.Utilities.ClientAPI;
import com.rishabh.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketPresenter implements MarketContract.presenter
{
    MarketContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public MarketPresenter(MarketContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getList() {
        clientAPI.getMarketPosition("Client").enqueue(new Callback<MarketResponse>() {
            @Override
            public void onResponse(Call<MarketResponse> call, Response<MarketResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showList(response.body());
                    }
                    else
                        mvpview.toast(response.body().getMessage());
                }
                else
                    mvpview.toast(response.message());
            }

            @Override
            public void onFailure(Call<MarketResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
