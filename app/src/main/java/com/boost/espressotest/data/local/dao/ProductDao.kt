package com.boost.espressotest.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import com.boost.espressotest.data.local.model.ProductEntity

import io.reactivex.Single

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE id LIKE :id")
    fun getProduct(id: Long): Single<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProducts(productList: List<ProductEntity>)

    @Query("UPDATE products SET favorite = :favorite WHERE id LIKE :id")
    fun updateProduct(id: Long, favorite: Boolean)
}
