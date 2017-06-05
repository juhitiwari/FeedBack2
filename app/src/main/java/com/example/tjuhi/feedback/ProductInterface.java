package com.example.tjuhi.feedback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tjuhi on 6/2/2017.
 */

public interface ProductInterface {
    @GET("/avg.php")
    Call<List<Product>> getProduct();
}
