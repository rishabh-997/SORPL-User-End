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

    @Override
    public void update(String name, String mobile, String phone, String email, String pan, String billto, String shipto, String gst, String bankname, String ifsc, String isc, String bankphone, String accno, String msmenumber, String preferred, final String regtype) {
        clientAPI.updateClient(name, mobile, phone, email, pan, billto, shipto, gst, bankname, ifsc, isc, bankphone, accno, msmenumber, preferred, regtype).enqueue(new Callback<com.example.sorpluserend.MyDetails.Model.Response>() {
            @Override
            public void onResponse(Call<com.example.sorpluserend.MyDetails.Model.Response> call, Response<com.example.sorpluserend.MyDetails.Model.Response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                        mvpview.showToast("Updated Successfully");
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<com.example.sorpluserend.MyDetails.Model.Response> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
