package com.boost.espressotest.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.boost.espressotest.data.local.dao.ProductDao
import com.boost.espressotest.data.local.model.ProductEntity

// TODO: 3/22/18 why you need brackets here?
@Database(entities = [(ProductEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // TODO: 3/22/18 why you need this here?
    companion object {
        const val APP_DATABASE_NAME = "app-database"
    }

    abstract fun productDao(): ProductDao

}
