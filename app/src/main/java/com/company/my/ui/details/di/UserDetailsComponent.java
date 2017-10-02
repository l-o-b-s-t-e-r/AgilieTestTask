package com.company.my.ui.details.di;

import com.company.my.app.di.FragmentScope;
import com.company.my.ui.details.UserDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by Lobster on 29.09.17.
 */

@FragmentScope
@Subcomponent(modules = {UserDetailsModule.class})
public interface UserDetailsComponent {

    void inject(UserDetailsFragment fragment);

}
