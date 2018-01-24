package com.boost.espressotest.app;

import android.app.Application;

import com.boost.espressotest.app.di.DependencyGraph;

/**
 * @author PerSpiKyliaTor on 19.01.18.
 */
public class EspressoTestApp extends Application {

    private static DependencyGraph mDependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mDependencyGraph = new DependencyGraph(this);
    }

    public static DependencyGraph getDependencyGraph() {
        return mDependencyGraph;
    }
}
