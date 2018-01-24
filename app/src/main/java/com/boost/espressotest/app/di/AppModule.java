package com.boost.espressotest.app.di;

import com.boost.espressotest.app.EspressoTestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class AppModule {

    private EspressoTestApp mEspressoTestApp;

    public AppModule(EspressoTestApp espressoTestApp) {
        mEspressoTestApp = espressoTestApp;
    }

    @Provides
    @Singleton
    EspressoTestApp provideMainApp() {
        return mEspressoTestApp;
    }
}
