package com.boost.espressotest.data.di;

import com.boost.espressotest.BuildConfig;
import com.boost.espressotest.app.EspressoTestApp;
import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.api.tools.ConnectivityInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    ConnectivityInterceptor provideConnectivityInterceptor(EspressoTestApp espressoTestApp) {
        return new ConnectivityInterceptor(espressoTestApp);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor, ConnectivityInterceptor connectivityInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(connectivityInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideMainApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
