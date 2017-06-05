package com.example.tjuhi.feedback;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by tjuhi on 6/2/2017.
 */

public interface WhereInterface {
    @FormUrlEncoded
    @POST("/get.php")
    public void where(
            @Field("mProductID") String mProductID,
           Callback<Response> callback
    );
}
