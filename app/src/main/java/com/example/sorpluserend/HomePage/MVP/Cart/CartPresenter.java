package com.example.sorpluserend.HomePage.MVP.Cart;

import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.CartResponse_CUD;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter implements CartContract.presenter
{
    CartContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public CartPresenter(CartContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getCart(String mobile, String company)
    {
        clientAPI.getCart(mobile,company).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if(response.isSuccessful())
                {
                    if (response.body().getMessage().equals("successful"))
                    {
                        mvpview.showCart(response.body());
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
    public void deleteCart(String mobile, String pid, final int pos)
    {
        clientAPI.deleteProduct(mobile,pid).enqueue(new Callback<CartResponse_CUD>() {
            @Override
            public void onResponse(Call<CartResponse_CUD> call, Response<CartResponse_CUD> response)
            {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.delete(pos,response.message());
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
            public void onFailure(Call<CartResponse_CUD> call, Throwable t) {

            }
        });
    }
}
