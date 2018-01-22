package com.boost.espressotest.data.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Module
public class LocalStorageModule {

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
