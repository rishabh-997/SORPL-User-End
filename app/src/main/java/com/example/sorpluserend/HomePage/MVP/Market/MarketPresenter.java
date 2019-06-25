package com.example.sorpluserend.HomePage.MVP.Market;

import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

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
    public void getMarket(String client, String company, String subcat) {
        clientAPI.getMarketPosition(client,subcat,company).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
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
            public void onFailure(Call<CartResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }

    @Override
    public void getCompany() {
        clientAPI.getCompany("9935685103").enqueue(new Callback<Comapny_response>() {
            @Override
            public void onResponse(Call<Comapny_response> call, Response<Comapny_response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showCompanies(response.body());
                    }
                    else
                        mvpview.toast(response.body().getMessage());
                }
                else
                    mvpview.toast(response.message());
            }
            @Override
            public void onFailure(Call<Comapny_response> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }

    @Override
    public void getSubCategory(String company) {
        clientAPI.getSubCat(company).enqueue(new Callback<SubCat_response>() {
            @Override
            public void onResponse(Call<SubCat_response> call, Response<SubCat_response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showSubCategories(response.body());
                    }
                    else
                        mvpview.toast(response.body().getMessage());
                }
                else
                    mvpview.toast(response.message());
            }

            @Override
            public void onFailure(Call<SubCat_response> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
