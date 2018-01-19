package com.boost.espressotest.presentation.screen.main.di;

import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.screen.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Module
public class MainModule {

    @Provides
    @MainScope
    MainPresenter provideMainPresenter(ProductRepository productRepository) {
        return new MainPresenter(productRepository);
    }
}
