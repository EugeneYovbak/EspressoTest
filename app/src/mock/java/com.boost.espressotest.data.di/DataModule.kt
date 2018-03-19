package com.boost.espressotest.data.di

import android.content.Context

import com.boost.espressotest.data.repository.ProductRepositoryImplMock
import com.boost.espressotest.domain.ProductRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideProductRepository(context: Context): ProductRepository {
        return ProductRepositoryImplMock(context)
    }
}
