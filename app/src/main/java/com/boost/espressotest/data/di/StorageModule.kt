package com.boost.espressotest.data.di

import android.content.Context
import androidx.room.Room
import com.boost.espressotest.data.local.AppDatabase
import com.boost.espressotest.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    companion object {
        const val APP_DATABASE_NAME = "app-database"
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    internal fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}
