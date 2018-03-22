package com.boost.espressotest.data.api

import com.boost.espressotest.BuildConfig
import com.boost.espressotest.data.api.model.BaseResponse
import com.boost.espressotest.data.api.model.ProductApi

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val AUTH_TOKEN = "Authorization: Token " + BuildConfig.LCBO_API_KEY

interface ApiService {

    @GET("/products")
    @Headers(AUTH_TOKEN)
    fun loadAllProductsByQuery(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<BaseResponse<List<ProductApi>>>
}
