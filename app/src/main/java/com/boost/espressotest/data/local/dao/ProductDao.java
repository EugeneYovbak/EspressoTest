package com.boost.espressotest.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.boost.espressotest.data.local.model.ProductEntity;

import java.util.List;

import io.reactivex.Single;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products WHERE id LIKE :id")
    Single<ProductEntity> getProduct(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(List<ProductEntity> productList);

    @Query("UPDATE products SET favorite = :favorite WHERE id LIKE :id")
    void updateProduct(long id, boolean favorite);
}
