package com.boost.espressotest.data.api.tools;

import android.support.annotation.NonNull;

import com.boost.espressotest.domain.exceptions.NoConnectivityException;
import com.boost.espressotest.presentation.tools.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author PerSpiKyliaTor on 11.04.2017.
 */

public class ConnectivityInterceptor implements Interceptor {

    private NetworkUtils mNetworkUtils;

    public ConnectivityInterceptor(NetworkUtils networkUtils) {
        mNetworkUtils = networkUtils;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!mNetworkUtils.isOnline()) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}