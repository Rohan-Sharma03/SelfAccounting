package com.therohansharma03.selfaccounting;

import com.therohansharma03.selfaccounting.allResponses.BillingResponse;
import com.therohansharma03.selfaccounting.allResponses.DeleteAccountResponse;
import com.therohansharma03.selfaccounting.allResponses.DeleteSellerDataResponse;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerData;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByCommodity;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByDate;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByFirstName;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllSellerDataByUniqueId;
import com.therohansharma03.selfaccounting.allResponses.FetchingAllUserData;
import com.therohansharma03.selfaccounting.allResponses.FetchingSellerDataBySrNoResponse;
import com.therohansharma03.selfaccounting.allResponses.LoginResponse;
import com.therohansharma03.selfaccounting.allResponses.PasswordUpdateResponse;
import com.therohansharma03.selfaccounting.allResponses.RebillingResponse;
import com.therohansharma03.selfaccounting.allResponses.RegisterResponse;
import com.therohansharma03.selfaccounting.allResponses.UserUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("Name") String Name,
            @Field("EmailId") String EmailId,
            @Field("Passwords") String Passwords,
            @Field("MobileNumber") String MobileNumber
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("EmailId") String EmailId,
            @Field("Passwords") String Passwords
    );

    @FormUrlEncoded
    @POST("deleteaccount.php")
    Call<DeleteAccountResponse> deleteUser(
            @Field("SrNo") int SrNo
    );

    @FormUrlEncoded
    @POST("updatepassword.php")
    Call<PasswordUpdateResponse> updatepassword(
            @Field("EmailId") String EmailId,
            @Field("Passwords") String Passwords,
            @Field("NewPasswords") String NewPasswords
    );

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<UserUpdateResponse> updateuser(
            @Field("SrNo") int SrNo,
            @Field("Name") String Name,
            @Field("MobileNumber") String MobileNumber
    );

    @FormUrlEncoded
    @POST("sellerdatauploading.php")
    Call<BillingResponse> uploadbilldata(
            @Field("FirstName") String FirstName,
            @Field("LastName") String LastName,
            @Field("Commodity") String Commodity,
            @Field("BasicRates") float BasicRates,
            @Field("Weight") float Weight,
            @Field("BeneficiaryAmount") float BeneficiaryAmount,
            @Field("PaymentMethod") String PaymentMethod,
            @Field("AccountNumber") String AccountNumber,
            @Field("IFSCode") String IFSCode,
            @Field("PurchaseDate") String PurchaseDate,
            @Field("MobileNumber") String MobileNumber,
            @Field("ResidentialAddress") String ResidentialAddress,
            @Field("CustomerOf") String CustomerOf
    );


    @FormUrlEncoded
    @POST("fetchingsellerdatabysrno.php")
    Call<FetchingSellerDataBySrNoResponse> ApiUpDateSellerDataBySrNo(
            @Field("CustomerOf") String CustomerOf,
            @Field("SrNo") String SrNo
    );

    @FormUrlEncoded
    @POST("sellerdatareuploading.php")
    Call<RebillingResponse> ApiReuploadBillData(
            @Field("SrNo") String SrNo,
            @Field("FirstName") String FirstName,
            @Field("LastName") String LastName,
            @Field("Commodity") String Commodity,
            @Field("BasicRates") float BasicRates,
            @Field("Weight") float Weight,
            @Field("BeneficiaryAmount") float BeneficiaryAmount,
            @Field("PaymentMethod") String PaymentMethod,
            @Field("AccountNumber") String AccountNumber,
            @Field("IFSCode") String IFSCode,
            @Field("PurchaseDate") String PurchaseDate,
            @Field("MobileNumber") String MobileNumber,
            @Field("ResidentialAddress") String ResidentialAddress,
            @Field("CustomerOf") String CustomerOf
    );


    @GET("fetchinguserdata.php")
    Call<FetchingAllUserData> ApiGetAllUserData(
    );

    @FormUrlEncoded
    @POST("deleterecord.php")
    Call<DeleteSellerDataResponse> ApiDeleteSeller(
            @Field("SrNo") String SrNo
    );
    @FormUrlEncoded
    @POST("fetchingsellerdata.php")
    Call<FetchingAllSellerData> ApiGetAllSellerData(
            @Field("CustomerOf") String CustomerOf
    );
    @FormUrlEncoded
    @POST("fetchingsellerdatabyname.php")
    Call<FetchingAllSellerDataByFirstName> ApiGetAllSellerDataByFirstName(
            @Field("CustomerOf") String CustomerOf,
            @Field("FirstName") String FirstName
    );
    @FormUrlEncoded
    @POST("fetchingsellerdatabycommodity.php")
    Call<FetchingAllSellerDataByCommodity> ApiGetAllSellerDataByCommodity(
            @Field("CustomerOf") String CustomerOf,
            @Field("Commodity") String Commodity
    );
//    @FormUrlEncoded
//    @POST("fetchingsellerdatabyuniqueid.php")
//    Call<FetchingAllSellerDataByUniqueId> ApiGetAllSellerDataByUniqueId(
//            @Field("CustomerOf") String CustomerOf,
//            @Field("SrNo") String SrNo
//    );

    @FormUrlEncoded
    @POST("fetchingsellerdatabydate.php")
    Call<FetchingAllSellerDataByDate> ApiGetAllSellerDataByDate(
            @Field("CustomerOf") String CustomerOf,
            @Field("Fromdate") String Fromdate,
            @Field("Todate") String Todate
    );

}
