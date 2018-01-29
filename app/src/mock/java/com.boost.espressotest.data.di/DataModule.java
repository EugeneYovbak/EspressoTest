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

    @Provides
    @Singleton
    ProductRepository provideProductRepository(Context context) {
        return new ProductRepositoryImplMock(context);
    }
}
