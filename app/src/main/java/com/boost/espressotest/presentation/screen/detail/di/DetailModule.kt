package com.boost.espressotest.presentation.screen.detail.di

import com.boost.espressotest.domain.ProductRepository
import com.boost.espressotest.presentation.screen.detail.presenter.DetailPresenter

import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    @DetailScope
    internal fun provideDetailPresenter(productRepository: ProductRepository): DetailPresenter {
        return DetailPresenter(productRepository)
    }
}
