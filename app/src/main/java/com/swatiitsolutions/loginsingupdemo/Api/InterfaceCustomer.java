package com.swatiitsolutions.loginsingupdemo.Api;

import com.swatiitsolutions.loginsingupdemo.Login.ClsLoginResponse;
import com.swatiitsolutions.loginsingupdemo.SingIn.ClsSingInResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceCustomer {
    @FormUrlEncoded
    @POST("customers.php")
    Call<ClsSingInResponse> postCustomer(@Field("flag") String flag,
                                         @Field("customer_name") String customer_name,
                                         @Field("mobile") String mobile,
                                         @Field("email") String email,
                                         @Field("address") String address,
                                         @Field("password") String password,
                                         @Field("user_type") String userType);
//    @FormUrlEncoded
//    @POST("customers.php")
//    Call<ClsCustomerResponse> updateCustomer(@Field("flag") String flag,
//                                             @Field("id") int id,
//                                             @Field("customer_name") String customer_name,
//                                             @Field("mobile") String mobile,
//                                             @Field("email") String email,
//                                             @Field("address") String address,
//                                             @Field("password") String password);

    @FormUrlEncoded
    @POST("login_customer.php")
    Call<ClsLoginResponse> login(@Field("mobile") String mobile,
                                 @Field("password") String password,
                                 @Field("fcm_token") String fcmToken);

 /*   @FormUrlEncoded
    @POST("customers.php")
    Call<ClsCustomerGetResponse> getCustomer(@Field("flag") String flag);


    @FormUrlEncoded
    @POST("customers.php")
    Call<ClsCommonResponse> changePassword(@Field("flag") String flag,
                                           @Field("password") String password,
                                           @Field("id") String id);
*/
}
/*

   */
