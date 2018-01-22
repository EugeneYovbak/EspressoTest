package com.boost.espressotest.app.di;

import com.boost.espressotest.data.di.ApiModule;
import com.boost.espressotest.data.di.DataModule;
import com.boost.espressotest.data.di.LocalStorageModule;
import com.boost.espressotest.presentation.screen.detail.di.DetailComponent;
import com.boost.espressotest.presentation.screen.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Component(modules = {AppModule.class, ApiModule.class, LocalStorageModule.class, DataModule.class})
@Singleton
public interface AppComponent {
    MainComponent plusMainComponent();

    DetailComponent plusDetailComponent();
}
