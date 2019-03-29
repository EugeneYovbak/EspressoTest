package com.boost.espressotest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
