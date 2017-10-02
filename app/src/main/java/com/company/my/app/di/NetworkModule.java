package com.company.my.app.di;

import com.company.my.api.UserApi;

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
public class NetworkModule {

    private static final String USER_ENDPOINT = "http://jsonplaceholder.typicode.com/";

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
}
