package com.boost.espressotest.data.di;

import android.arch.persistence.room.Room;

import com.boost.espressotest.app.EspressoTestApp;
import com.boost.espressotest.data.local.AppDatabase;
import com.boost.espressotest.data.local.dao.ProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Module
public class StorageModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(EspressoTestApp espressoTestApp) {
        return Room.databaseBuilder(espressoTestApp.getApplicationContext().getApplicationContext(),
                AppDatabase.class, AppDatabase.APP_DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    ProductDao provideProductDao(AppDatabase appDatabase) {
        return appDatabase.productDao();
    }
}
