package com.boost.espressotest.presentation.base.di


import com.boost.espressotest.presentation.screen.detail.di.DetailModule
import com.boost.espressotest.presentation.screen.detail.di.DetailScope
import com.boost.espressotest.presentation.screen.detail.view.DetailActivity
import com.boost.espressotest.presentation.screen.main.di.MainModule
import com.boost.espressotest.presentation.screen.main.di.MainScope
import com.boost.espressotest.presentation.screen.main.view.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    @MainScope
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(DetailModule::class)])
    @DetailScope
    internal abstract fun contributeDetailActivity(): DetailActivity
}
