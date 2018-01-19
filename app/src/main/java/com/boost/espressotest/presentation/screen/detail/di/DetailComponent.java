package com.boost.espressotest.presentation.screen.detail.di;

import com.boost.espressotest.presentation.screen.detail.view.DetailActivity;

import dagger.Subcomponent;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Subcomponent(modules = DetailModule.class)
@DetailScope
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
