package com.boost.espressotest.app.di;

import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.presentation.screen.detail.di.DetailComponent;
import com.boost.espressotest.presentation.screen.main.di.MainComponent;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

// TODO: 1/23/18 later, update for android dagger  
public class DependencyGraph {

    private AppComponent mAppComponent;

    private MainComponent mMainComponent;
    private DetailComponent mDetailComponent;

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

    public DetailComponent initDetailComponent() {
        mDetailComponent = mAppComponent.plusDetailComponent();
        return mDetailComponent;
    }

    public void releaseDetailComponent() {
        mDetailComponent = null;
    }
}
