package com.boost.espressotest.presentation.screen.detail.di;

import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.screen.detail.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class DetailModule {

    @Provides
    @DetailScope
    DetailPresenter provideDetailPresenter(ProductRepository productRepository) {
        return new DetailPresenter(productRepository);
    }
}
