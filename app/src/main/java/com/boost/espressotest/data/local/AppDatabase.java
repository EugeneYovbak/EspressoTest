package com.boost.espressotest.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.boost.espressotest.data.local.dao.ProductDao;
import com.boost.espressotest.data.local.model.ProductContent;

/**
 * @author PerSpiKyliaTor on 22.01.18.
 */

@Database(entities = {ProductContent.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String APP_DATABASE_NAME = "app-database";

    public abstract ProductDao productDao();
}
