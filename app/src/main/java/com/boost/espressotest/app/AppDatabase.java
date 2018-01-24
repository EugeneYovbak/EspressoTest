package com.boost.espressotest.app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.boost.espressotest.data.content.ProductContent;
import com.boost.espressotest.data.dao.ProductDao;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */
// TODO: 1/24/18 move to data module
@Database(entities = {ProductContent.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String APP_DATABASE_NAME = "app-database";

    public abstract ProductDao productDao();
}
