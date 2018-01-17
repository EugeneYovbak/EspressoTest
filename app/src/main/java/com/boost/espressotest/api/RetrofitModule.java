package com.boost.espressotest.api;

import com.boost.espressotest.BuildConfig;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public class RetrofitModule {

    public static ApiInterface getApiInterface() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
        builder.client(getOkHttpClient());

        return builder.build().create(ApiInterface.class);
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpInterceptor = new HttpLoggingInterceptor();
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpInterceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }
}
