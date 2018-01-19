package com.boost.espressotest.app.di;

import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.presentation.screen.main.di.MainComponent;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class DependencyGraph {

    private AppComponent mAppComponent;

    private MainComponent mMainComponent;

    public DependencyGraph(MainApp mainApp) {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mainApp))
                .build();
    }

    public MainComponent initMainComponent() {
        mMainComponent = mAppComponent.plusMainComponent();
        return mMainComponent;
    }

    public void releaseMainComponent() {
        mMainComponent = null;
    }
}
