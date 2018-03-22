package com.boost.espressotest.data.api.tools

import android.content.Context
import com.boost.espressotest.domain.exceptions.NoConnectivityException
import com.boost.espressotest.presentation.tools.isOnline
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isOnline()) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}