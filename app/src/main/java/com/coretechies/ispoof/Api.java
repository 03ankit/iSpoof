package com.coretechies.ispoof;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {

    String BASE_URL = "https://api.ispgp.com";
    //Call<String>  getsuperHeroes();
   // Call<String> sendFeedback(@Body FeedbackBody body);

    @POST("/api/v1/get-dtmf")
    @FormUrlEncoded
    Call<ResponseBody> savePost(@Field("token") String token,
                                @Field("username") String username,
                                @Field("phone_number") String phone_number,
                                @Field("callerid") String callerid,
                                @Field("password") String password);


}
