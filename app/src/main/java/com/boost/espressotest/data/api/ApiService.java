package com.boost.espressotest.data.api;

import com.boost.espressotest.BuildConfig;
import com.boost.espressotest.data.model.ApiResponse;
import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface ApiService {

    String AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY;

    @GET("/products")
    @Headers(AUTH_TOKEN)
    Single<ApiResponse<List<Product>>> loadAllProductsByQuery(@Query("page") Integer page, @Query("per_page") Integer perPage);

    @GET("/products/{id}")
    @Headers(AUTH_TOKEN)
    Single<ApiResponse<Product>> loadProductById(@Path("id") Long productId);
}
