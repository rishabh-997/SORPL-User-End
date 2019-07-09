package com.example.sorpluserend.CompanyData.MVP;

import com.example.sorpluserend.CompanyData.Model.DataResponse;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyPresenter implements CompanyContract.presenter
{
    CompanyContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public CompanyPresenter(CompanyContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getPDF()
    {
        clientAPI.getPDF("9935685103").enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showList(response.body());
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
            public void onFailure(Call<DataResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });

    }
}
