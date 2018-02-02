package com.boost.espressotest.app.di;

import com.boost.espressotest.app.EspressoTestApp;
import com.boost.espressotest.data.di.ApiModule;
import com.boost.espressotest.data.di.DataModule;
import com.boost.espressotest.data.di.StorageModule;
import com.boost.espressotest.presentation.base.di.ActivityBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Component(modules = {AndroidInjectionModule.class, ActivityBuilder.class, AppModule.class, ApiModule.class, DataModule.class, StorageModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<EspressoTestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(EspressoTestApp espressoTestApp);

        AppComponent build();
    }
}
