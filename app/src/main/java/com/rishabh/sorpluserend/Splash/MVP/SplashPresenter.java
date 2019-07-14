package com.rishabh.sorpluserend.Splash.MVP;

import com.rishabh.sorpluserend.Splash.Model.VersionResponse;
import com.rishabh.sorpluserend.Utilities.ClientAPI;
import com.rishabh.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements SplashContract.presenter
{
    SplashContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public SplashPresenter(SplashContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getVersion() {
        clientAPI.getVersion("9935685103").enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.checkVersion(response.body());
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
