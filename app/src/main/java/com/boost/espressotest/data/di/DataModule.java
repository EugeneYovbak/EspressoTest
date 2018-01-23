package com.boost.espressotest.data.di;

import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.dao.ProductDao;
import com.boost.espressotest.data.repository.ProductRepositoryImpl;
import com.boost.espressotest.domain.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ApiService apiService, ProductDao productDao) {
        return new ProductRepositoryImpl(apiService, productDao);
    }

    //MOCK
//    @Provides
//    @Singleton
//    ProductRepository provideProductRepository(MainApp mainApp) {
//        return new ProductRepositoryImplMock(mainApp.getApplicationContext());
//    }
}
