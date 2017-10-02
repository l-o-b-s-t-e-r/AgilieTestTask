package com.company.my.ui.users.di;

import com.company.my.app.di.FragmentScope;
import com.company.my.ui.users.UsersFragment;

import dagger.Subcomponent;

/**
 * Created by Lobster on 29.09.17.
 */

@FragmentScope
@Subcomponent(modules = {UsersModule.class})
public interface UsersComponent {

    void inject(UsersFragment fragment);

}
