package com.boost.espressotest.app.di;

import com.boost.espressotest.app.MainApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class AppModule {

    private MainApp mMainApp;

    public AppModule(MainApp mainApp) {
        mMainApp = mainApp;
    }

    @Provides
    @Singleton
    MainApp provideMainApp() {
        return mMainApp;
    }
}
