package com.example.sorpluserend.MyDetails.MVP;

import com.example.sorpluserend.MyDetails.Model.ResponseClient;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPreseenter implements DetailContract.presenter
{
    DetailContract.view mvpview;
    ClientAPI clientAPI=Utils.getClientAPI();

    public DetailPreseenter(DetailContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void search(String query)
    {
        clientAPI.search("0",query).enqueue(new Callback<ResponseClient>() {
            @Override
            public void onResponse(Call<ResponseClient> call, Response<ResponseClient> response)
            {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                        mvpview.showData(response.body());
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<ResponseClient> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
