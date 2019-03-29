package com.boost.espressotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.boost.espressotest.data.local.dao.ProductDao
import com.boost.espressotest.data.local.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
