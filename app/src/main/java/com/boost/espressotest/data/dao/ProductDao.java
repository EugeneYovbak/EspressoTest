package com.boost.espressotest.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.boost.espressotest.data.content.ProductContent;

import java.util.List;

import io.reactivex.Single;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products WHERE id LIKE :id")
    Single<ProductContent> getProduct(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(List<ProductContent> productList);

    @Update
    void updateProduct(ProductContent product);
}
