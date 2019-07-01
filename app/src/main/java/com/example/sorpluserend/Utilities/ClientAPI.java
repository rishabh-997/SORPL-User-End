package com.example.sorpluserend.Utilities;
import com.example.sorpluserend.ContactUs.Model.ContactResponse;
import com.example.sorpluserend.History.Model.HistoryDetailResponse;
import com.example.sorpluserend.History.Model.HistoryResponse;
import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.CartResponse_CUD;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.EnquiryResponse;
import com.example.sorpluserend.HomePage.Model.MarketResponse;
import com.example.sorpluserend.HomePage.Model.Product_Response;
import com.example.sorpluserend.HomePage.Model.SpecResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.HomePage.Model.UnitResponse;
import com.example.sorpluserend.LogIn.Model.LogInResponse;
import com.example.sorpluserend.OTP.Model.OTPResponse;
import com.example.sorpluserend.SignUp.Model.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientAPI
{

    @POST("login/")
    @FormUrlEncoded
    Call<LogInResponse> login(
            @Field("Mobile") String mobile,
            @Field("fcm") String fcm
    );

    @POST("verify/")
    @FormUrlEncoded
    Call<OTPResponse> verify(
            @Field("Mobile") String mobile,
            @Field("OTP") String otp
    );

    @POST("new_registrations/")
    @FormUrlEncoded
    Call<ResponseBody> createClient(
            @Field("Name") String name,
            @Field("Mobile") String mobile,
            @Field("Email") String email,
            @Field("CompanyName") String company,
            @Field("RegType") String client);

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

    @POST("AddCartClient/")
    @FormUrlEncoded
    Call<ResponseBody> addCart(
            @Field("MobileNumber") String mobile,
            @Field("PID") String pid,
            @Field("Size") String size,
            @Field("Unit") String unit
    );

    @POST("CartDetails/")
    @FormUrlEncoded
    Call<CartResponse> getCart(
            @Field("MobileNumber") String mobile,
            @Field("Company") String company
    );
    @POST("DeleteCart/")
    @FormUrlEncoded
    Call<CartResponse_CUD> deleteProduct(
            @Field("ID") String id
    );

    @POST("MarketDetails/")
    @FormUrlEncoded
    Call<MarketResponse> getMarketPosition(
            @Field("UserType") String user
    );

    @POST("contact_us/")
    @FormUrlEncoded
    Call<ContactResponse> getContact(
            @Field("anything") String mob
    );

    @POST("SendProductEnquiry/")
    @FormUrlEncoded
    Call<EnquiryResponse> sendEnquiry(
            @Field("access_token") String token
    );

    @POST("AddEnquiry/")
    @FormUrlEncoded
    Call<EnquiryResponse> sendEmail(
            @Field("Name") String name,
            @Field("MobileNumber") String mobile,
            @Field("EmailId") String email,
            @Field("Company") String company,
            @Field("Message") String message
    );

    @POST("GetOrderHistory/")
    @FormUrlEncoded
    Call<HistoryResponse> getOrderHistory(
            @Field("MobileNumber") String mob,
            @Field("Contact_Type") String type,
            @Field("Company") String company
    );

    @POST("GetOrderDetailsHistory/")
    @FormUrlEncoded
    Call<HistoryDetailResponse> getOrderDetailsHistory(
            @Field("OrderId") String id
    );
    @POST("GetUnitData/")
    @FormUrlEncoded
    Call<UnitResponse> getUnits(
            @Field("mob") String mob
    );
}
