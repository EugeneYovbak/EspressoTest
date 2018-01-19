package com.boost.espressotest.app;

import android.app.Application;

import com.boost.espressotest.app.di.DependencyGraph;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */

public class MainApp extends Application {

    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
