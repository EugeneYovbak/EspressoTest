package com.boost.espressotest.app.di;

import com.boost.espressotest.data.di.ApiModule;
import com.boost.espressotest.data.di.DataModule;
import com.boost.espressotest.data.di.StorageModule;
import com.boost.espressotest.presentation.screen.detail.di.DetailComponent;
import com.boost.espressotest.presentation.screen.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

// TODO: 1/23/18 remove autogeneraged sing for class header

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Component(modules = {AppModule.class, ApiModule.class, DataModule.class, StorageModule.class})
@Singleton
public interface AppComponent {
    MainComponent plusMainComponent();

    DetailComponent plusDetailComponent();
}
