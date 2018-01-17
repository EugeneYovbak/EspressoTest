package com.boost.espressotest.api;

import com.boost.espressotest.BuildConfig;
import com.boost.espressotest.model.ApiResponse;
import com.boost.espressotest.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public interface ApiInterface {

    String AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY;

    @GET("/products")
    @Headers(AUTH_TOKEN)
    Call<ApiResponse<List<Product>>> loadAllProductsByQuery(@Query("q") String query, @Query("where") String where, @Query("page") Integer page);

    @GET("/products/{id}")
    @Headers(AUTH_TOKEN)
    Call<ApiResponse<Product>> loadProductById(@Path("id") Long productId);
}
