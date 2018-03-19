package com.boost.espressotest.presentation.screen.main.di

import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter

import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @MainScope
    internal fun provideMainPresenter(productRepository: ProductRepository): MainPresenter {
        return MainPresenter(productRepository)
    }
}
