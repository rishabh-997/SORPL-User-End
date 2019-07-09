package com.example.sorpluserend.HomePage.MVP.Product;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_response;

import com.example.sorpluserend.HomePage.Model.UnitResponse;
import com.example.sorpluserend.SignUp.Model.ResponseBody;
import com.example.sorpluserend.Utilities.ClientAPI;
import com.example.sorpluserend.Utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductContract.presenter
{
    ProductContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public ProductPresenter(ProductContract.view mvpview) {
        this.mvpview = mvpview;
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
                        mvpview.showtaost(response.body().getMessage());
                }
                else
                    mvpview.showtaost(response.message());
            }
            @Override
            public void onFailure(Call<Comapny_response> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
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
                        mvpview.showtaost(response.body().getMessage());
                }
                else
                    mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<SubCat_response> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }

    @Override
    public void getList(String subcat,String company) {
        clientAPI.getProductList("client",subcat,company).enqueue(new Callback<Product_Response>() {
            @Override
            public void onResponse(Call<Product_Response> call, Response<Product_Response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showdata(response.body());
                    }
                    else
                        mvpview.showtaost(response.body().getMessage());
                }
                else
                    mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<Product_Response> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }

    @Override
    public void getSpecs(String name) {
        clientAPI.getSpecs(name).enqueue(new Callback<SpecResponse>() {
            @Override
            public void onResponse(Call<SpecResponse> call, Response<SpecResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showSpecs(response.body());
                    }
                    else
                        mvpview.showtaost(response.body().getMessage());
                }
                else
                    mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<SpecResponse> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }

    @Override
    public void addCart(String pid, String mobile,String size, String unit,String nvm) {
        clientAPI.addCart(mobile,pid,unit,size,nvm).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Successful"))
                        mvpview.updateMyCart("Successfully added to cart");
                    else
                        mvpview.showtaost(response.body().getMessage());
                }
                else
                    mvpview.showtaost(response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }

    @Override
    public void getUnit() {
        clientAPI.getUnits("").enqueue(new Callback<UnitResponse>() {
            @Override
            public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                mvpview.setList(response.body());
            }

            @Override
            public void onFailure(Call<UnitResponse> call, Throwable t) {
                mvpview.showtaost(t.getMessage());
            }
        });
    }
}
