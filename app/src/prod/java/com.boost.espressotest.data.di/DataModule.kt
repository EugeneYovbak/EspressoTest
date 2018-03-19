package com.boost.espressotest.data.di

import com.boost.espressotest.data.api.ApiService
import com.boost.espressotest.data.local.dao.ProductDao
import com.boost.espressotest.data.repository.ProductRepositoryImpl
import com.boost.espressotest.domain.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    internal fun provideProductRepository(apiService: ApiService, productDao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(apiService, productDao)
    }
}
