package com.boost.espressotest.data.di;

import com.boost.espressotest.app.MainApp;
import com.boost.espressotest.data.dao.ProductDao;
import com.boost.espressotest.data.database.AppDatabase;

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
    AppDatabase provideAppDatabase(MainApp mainApp) {
        return AppDatabase.getAppDatabase(mainApp.getApplicationContext());
    }

    @Provides
    @Singleton
    ProductDao provideProductDao(AppDatabase appDatabase) {
        return appDatabase.productDao();
    }
}
