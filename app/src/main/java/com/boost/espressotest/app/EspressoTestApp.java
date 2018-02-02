package com.boost.espressotest.app;

import com.boost.espressotest.app.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */
public class EspressoTestApp extends DaggerApplication {

    @Override
    protected AndroidInjector<EspressoTestApp> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
