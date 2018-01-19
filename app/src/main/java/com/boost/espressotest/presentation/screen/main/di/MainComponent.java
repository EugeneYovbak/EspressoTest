package com.boost.espressotest.presentation.screen.main.di;

import com.boost.espressotest.presentation.screen.main.view.MainActivity;

import dagger.Subcomponent;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

@Subcomponent(modules = MainModule.class)
@MainScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
