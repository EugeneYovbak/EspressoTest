package com.boost.espressotest.app.di

import com.boost.espressotest.app.EspressoTestApp
import com.boost.espressotest.data.di.ApiModule
import com.boost.espressotest.data.di.DataModule
import com.boost.espressotest.data.di.StorageModule
import com.boost.espressotest.presentation.base.di.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    AppModule::class,
    ApiModule::class,
    DataModule::class,
    StorageModule::class])
@Singleton
interface AppComponent : AndroidInjector<EspressoTestApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(espressoTestApp: EspressoTestApp): Builder

        fun build(): AppComponent
    }
}
