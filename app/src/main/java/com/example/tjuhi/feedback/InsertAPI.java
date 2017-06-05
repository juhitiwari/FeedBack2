package com.example.tjuhi.feedback;

import java.util.Date;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by tjuhi on 6/1/2017.
 */

public interface InsertAPI {
    @FormUrlEncoded
    @POST("/insert.php")
    public void insertUser(
            @Field("mProductID") String mProductID,
            @Field("user")String user,
            @Field("rating")float rating,
            @Field("comment") String comment,
            @Field("date")String date,

            Callback<Response> callback);
}
