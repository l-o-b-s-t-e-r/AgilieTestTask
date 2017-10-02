package com.company.my.app.di;

import com.company.my.api.UserApi;
import com.company.my.model.manager.DataManager;
import com.company.my.model.manager.IDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lobster on 29.09.17.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    public IDataManager provideDataManager(UserApi userApi) {
        return new DataManager(userApi);
    }
}
