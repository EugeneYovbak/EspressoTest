package com.boost.espressotest.app.di

import android.content.Context
import com.boost.espressotest.app.EspressoTestApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(espressoTestApp: EspressoTestApp): Context {
        return espressoTestApp.applicationContext
    }
}
