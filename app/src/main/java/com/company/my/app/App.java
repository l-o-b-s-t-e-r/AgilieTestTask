package com.company.my.app;

import android.app.Application;

import com.company.my.app.di.AppComponent;
import com.company.my.app.di.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Lobster on 29.09.17.
 */

public class App extends Application {

    private static App instance;
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this);
        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build());

        component = DaggerAppComponent.builder()
                .build();
    }

}
