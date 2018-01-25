package com.boost.espressotest.data.di;

import android.content.Context;

import com.boost.espressotest.data.repository.ProductRepositoryImplMock;
import com.boost.espressotest.domain.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class DataModule {

//    @Provides
//    @Singleton
//    ProductRepository provideProductRepository(ApiService apiService, ProductDao productDao) {
//        return new ProductRepositoryImpl(apiService, productDao);
//    }

    //TODO: 1/24/18 mock should be a flavor, so you can configure build options and run tests on mock build
    @Provides
    @Singleton
    ProductRepository provideProductRepository(Context context) {
        return new ProductRepositoryImplMock(context);
    }
}
