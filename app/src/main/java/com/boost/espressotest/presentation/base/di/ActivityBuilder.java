package com.boost.espressotest.presentation.base.di;


import com.boost.espressotest.presentation.screen.detail.di.DetailModule;
import com.boost.espressotest.presentation.screen.detail.di.DetailScope;
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity;
import com.boost.espressotest.presentation.screen.main.di.MainModule;
import com.boost.espressotest.presentation.screen.main.di.MainScope;
import com.boost.espressotest.presentation.screen.main.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainModule.class)
    @MainScope
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = DetailModule.class)
    @DetailScope
    abstract DetailActivity contributeDetailActivity();
}
