package com.example.sorpluserend.SignUp.MVP;

import com.example.sorpluserend.SignUp.Model.ResponseBody;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements SignUpContract.presenter
{
    SignUpContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public SignUpPresenter(SignUpContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void createUser(String name, String mobile, String email, String company, String client) {
        clientAPI.createClient(name, mobile, email, company,client).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                    {
                        mvpview.success();
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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
