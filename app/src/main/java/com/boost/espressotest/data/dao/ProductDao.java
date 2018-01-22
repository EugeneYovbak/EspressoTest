package com.boost.espressotest.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.boost.espressotest.domain.model.Product;

import io.reactivex.Single;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM favorite_products WHERE id LIKE :id")
    Single<Product> getProductById(long id);

    @Insert
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);
}
