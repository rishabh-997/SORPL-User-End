package com.example.sorpluserend.Utilities;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientAPI
{
    /*
    @POST("admin_login/")
    @FormUrlEncoded
    Call<LogInResponse> login(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("fcm") String fcm
    );

    @POST("new_registrations/")
    @FormUrlEncoded
    Call<ResponseBody> createClient(
            @Field("Name") String name,
            @Field("Mobile") String mobile,
            @Field("Phone") String phone,
            @Field("Email") String email,
            @Field("Pan") String pan,
            @Field("BillTo") String billto,
            @Field("ShipTo") String shipto,
            @Field("GSTNo") String gst,
            @Field("BankName") String bankname,
            @Field("IFSCNo") String ifsc,
            @Field("ISCCode") String isc,
            @Field("BankPhone") String bankphone,
            @Field("AccountNo") String acccountno,
            @Field("MSMENo") String msme,
            @Field("TransportNo") String transport,
            @Field("RegType") String reg);
            */

    @POST("ProductList/")
    @FormUrlEncoded
    Call<Product_Response> getProductList(
            @Field("UserType") String usertype,
            @Field("SubCatergory") String subcat,
            @Field("Company") String company
    );

    @POST("CompanyList/")
    @FormUrlEncoded
    Call<Comapny_response> getCompany(
            @Field("Mob") String mob
    );

    @POST("SubCateogry/")
    @FormUrlEncoded
    Call<SubCat_response> getSubCat(
            @Field("Company") String company
    );

    @POST("SpecsDetails/")
    @FormUrlEncoded
    Call<SpecResponse> getSpecs(
            @Field("ProductName") String prodname
    );

    /*
    @POST("GetUnitData/")
    @FormUrlEncoded
    Call<UnitResponse> getUnits(
            @Field("mob") String mob
    );

    */
}
