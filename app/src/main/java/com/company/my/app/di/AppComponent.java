package com.company.my.app.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lobster on 29.09.17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
}
