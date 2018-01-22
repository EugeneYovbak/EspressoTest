package com.boost.espressotest.data.di;

import com.boost.espressotest.data.api.ApiService;
import com.boost.espressotest.data.repository.ProductRepositoryImpl;
import com.boost.espressotest.domain.ProductRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ApiService apiService, Realm realm) {
        return new ProductRepositoryImpl(apiService, realm);
    }
}
