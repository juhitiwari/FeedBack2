package com.example.tjuhi.feedback;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.RequestInterceptor;
import retrofit.client.OkClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tjuhi on 6/1/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://ric-tiiciiitm.webhostingforstudents.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }
        return  retrofit;
    }
}
