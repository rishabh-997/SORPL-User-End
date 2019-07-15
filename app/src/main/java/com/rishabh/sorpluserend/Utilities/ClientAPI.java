package com.rishabh.sorpluserend.Utilities;
import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;
import com.rishabh.sorpluserend.CompanyData.Model.DataResponse;
import com.rishabh.sorpluserend.ContactUs.Model.ContactResponse;
import com.rishabh.sorpluserend.Help.Model.HelpResponse;
import com.rishabh.sorpluserend.History.Model.HistoryDetailResponse;
import com.rishabh.sorpluserend.History.Model.HistoryResponse;
import com.rishabh.sorpluserend.HomePage.Model.CartResponse;
import com.rishabh.sorpluserend.HomePage.Model.CartResponse_CUD;
import com.rishabh.sorpluserend.HomePage.Model.Comapny_response;
import com.rishabh.sorpluserend.HomePage.Model.EnquiryResponse;
import com.rishabh.sorpluserend.HomePage.Model.MarketResponse;
import com.rishabh.sorpluserend.HomePage.Model.Product_Response;
import com.rishabh.sorpluserend.HomePage.Model.SpecResponse;
import com.rishabh.sorpluserend.HomePage.Model.SubCat_response;
import com.rishabh.sorpluserend.HomePage.Model.UnitResponse;
import com.rishabh.sorpluserend.LogIn.Model.LogInResponse;
import com.rishabh.sorpluserend.MyDetails.Model.Response;
import com.rishabh.sorpluserend.MyDetails.Model.ResponseClient;
import com.rishabh.sorpluserend.OTP.Model.OTPResponse;
import com.rishabh.sorpluserend.SignUp.Model.ResponseBody;
import com.rishabh.sorpluserend.Splash.Model.VersionResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ClientAPI
{

    @POST("splash_screen/")
    @FormUrlEncoded
    Call<VersionResponse> getVersion(
            @Field("mob") String mob
    );

    @POST("UpdateRegistrations/")
    @FormUrlEncoded
    Call<Response> updateClient(
            @Field("Name")String name,
            @Field("Mobile")String mobile,
            @Field("Phone")String phone,
            @Field("Email")String email,
            @Field("Pan")String pan,
            @Field("BillTo")String billto,
            @Field("ShipTo")String shipto,
            @Field("GSTNo")String gst,
            @Field("BankName")String bankname,
            @Field("IFSCNo")String ifsc,
            @Field("ISCCode")String isc,
            @Field("BankPhone")String bankphone,
            @Field("AccountNo")String acccountno,
            @Field("MSMENo")String msme,
            @Field("TransportNo")String transport,
            @Field("RegType")String reg
    );

    @POST("Search/")
    @FormUrlEncoded
    Call<ResponseClient> search(
            @Field("RegType") String regtype,
            @Field("query") String query
    );

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
            @Field("Unit") String unit,
            @Field("NVM") String nvm
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

    @POST("DocumentDetails/")
    @FormUrlEncoded
    Call<DataResponse> getPDF(
            @Field("mob") String mobile
    );

    @POST("Help/")
    @FormUrlEncoded
    Call<HelpResponse> getHelp(
            @Field("mob") String mob
    );

    @POST("AffiliationDetails/")
    @FormUrlEncoded
    Call<AffiliationResponse> getAffiliation(
            @Field("mob") String mob
    );
}
