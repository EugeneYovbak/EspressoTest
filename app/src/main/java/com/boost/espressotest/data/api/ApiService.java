package com.boost.espressotest.data.api;

import com.boost.espressotest.BuildConfig;
import com.boost.espressotest.data.response.BaseResponse;
import com.boost.espressotest.data.response.ProductResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */
// TODO: 1/24/18 reorganize packages by meaning (api{model, mapper, ...}, local {...}
public interface ApiService {

    String AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY;

    @GET("/products")
    @Headers(AUTH_TOKEN)
    Single<BaseResponse<List<ProductResponse>>> loadAllProductsByQuery(@Query("page") Integer page, @Query("per_page") Integer perPage);
}
