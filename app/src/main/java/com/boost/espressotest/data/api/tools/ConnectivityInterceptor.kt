package com.boost.espressotest.data.api.tools

import com.boost.espressotest.domain.exceptions.NoConnectivityException
import com.boost.espressotest.presentation.tools.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val networkUtils: NetworkUtils) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.isOnline) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}