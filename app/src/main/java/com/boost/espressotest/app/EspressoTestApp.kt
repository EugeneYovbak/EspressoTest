package com.boost.espressotest.app

import com.boost.espressotest.app.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class EspressoTestApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<EspressoTestApp> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
