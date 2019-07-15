package com.rishabh.sorpluserend.Affiliation.MVP;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;
import com.rishabh.sorpluserend.Utilities.ClientAPI;
import com.rishabh.sorpluserend.Utilities.Utils;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AffiliationPresenter implements AffiliationContract.presenter
{
    AffiliationContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public AffiliationPresenter(AffiliationContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getList() {
        clientAPI.getAffiliation("9935685103").enqueue(new Callback<AffiliationResponse>() {
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
