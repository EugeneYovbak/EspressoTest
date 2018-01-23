package com.boost.espressotest.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.boost.espressotest.domain.model.Product;

import java.util.List;

import io.reactivex.Single;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM favorite_products WHERE id LIKE :id")
    Single<Product> getProduct(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(List<Product> productList);

    @Update
    void updateProduct(Product product);
}
