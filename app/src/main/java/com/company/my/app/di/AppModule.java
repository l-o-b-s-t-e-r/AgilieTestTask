package com.company.my.app.di;

import com.company.my.api.AvatarApi;
import com.company.my.api.UserApi;
import com.company.my.model.manager.DataManager;
import com.company.my.model.manager.IDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lobster on 29.09.17.
 */

@Module
public class AppModule {

    private static final String USER_ENDPOINT = "http://jsonplaceholder.typicode.com/";
    private static final String AVATAR_ENDPOINT = "https://avatars.io/";

    @Provides
    @Singleton
    public UserApi provideUserApi() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(USER_ENDPOINT)
                .build()
                .create(UserApi.class);
    }

    @Provides
    @Singleton
    public AvatarApi provideAvatarApi() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AVATAR_ENDPOINT)
                .build()
                .create(AvatarApi.class);
    }

    @Provides
    @Singleton
    public IDataManager provideDataManager(UserApi userApi, AvatarApi avatarApi) {
        return new DataManager(userApi, avatarApi);
    }
}
