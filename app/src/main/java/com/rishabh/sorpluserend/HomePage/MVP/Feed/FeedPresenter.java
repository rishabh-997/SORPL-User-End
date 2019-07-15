package com.rishabh.sorpluserend.HomePage.MVP.Feed;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;
import com.rishabh.sorpluserend.Utilities.ClientAPI;
import com.rishabh.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPresenter implements FeedContract.presenter
{
    FeedContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public FeedPresenter(FeedContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getFeeds() {
        clientAPI.getFeed("9935685103").enqueue(new Callback<AffiliationResponse>() {
            @Override
            public void onResponse(Call<AffiliationResponse> call, Response<AffiliationResponse> response) {
                if(response.isSuccessful())
                {
                    if (response.body().getMessage().equals("successful"))
                    {
                        mvpview.showList(response.body());
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
            public void onFailure(Call<AffiliationResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
