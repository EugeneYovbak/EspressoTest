package com.boost.espressotest.data.api.tools;

import android.content.Context;
import android.support.annotation.NonNull;

import com.boost.espressotest.presentation.tools.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author PerSpiKyliaTor on 11.04.2017.
 */

public class ConnectivityInterceptor implements Interceptor {

    private Context mContext;

    // TODO: 1/24/18 create NetworkUtils(context) class with method isOnline and provide it here to avoid static methods (for testing purposes)
    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!Utils.isOnline(mContext)) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}