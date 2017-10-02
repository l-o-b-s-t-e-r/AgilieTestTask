package com.company.my.app.di;

import com.company.my.ui.details.di.UserDetailsComponent;
import com.company.my.ui.details.di.UserDetailsModule;
import com.company.my.ui.users.di.UsersComponent;
import com.company.my.ui.users.di.UsersModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lobster on 29.09.17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    UsersComponent plus(UsersModule module);

    UserDetailsComponent plus(UserDetailsModule module);

}
