package com.boost.espressotest.data.api;

import com.boost.espressotest.BuildConfig;
import com.boost.espressotest.data.api.model.BaseResponse;
import com.boost.espressotest.data.api.model.ProductApi;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public interface ApiService {

    String AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY;

    @GET("/products")
    @Headers(AUTH_TOKEN)
    Single<BaseResponse<List<ProductApi>>> loadAllProductsByQuery(@Query("page") Integer page, @Query("per_page") Integer perPage);
}
