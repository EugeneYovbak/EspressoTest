package com.boost.espressotest.app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.boost.espressotest.data.dao.ProductDao;
import com.boost.espressotest.domain.model.Product;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String APP_DATABASE_NAME = "app-database";

    public abstract ProductDao productDao();
}
