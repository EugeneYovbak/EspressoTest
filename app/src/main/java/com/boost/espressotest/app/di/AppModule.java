package com.boost.espressotest.app.di;

import android.content.Context;

import com.boost.espressotest.app.EspressoTestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(EspressoTestApp espressoTestApp) {
        return espressoTestApp.getApplicationContext();
    }
}
