package com.company.my.ui.details.di;

import com.company.my.app.di.FragmentScope;
import com.company.my.model.manager.IDataManager;
import com.company.my.ui.details.presenter.IUserDetailsPresenter;
import com.company.my.ui.details.presenter.UserDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lobster on 29.09.17.
 */

@Module
public class UserDetailsModule {

    private IUserDetailsPresenter.View mView;

    public UserDetailsModule(IUserDetailsPresenter.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    public UserDetailsPresenter providePresenter(IDataManager dataManager) {
        return new UserDetailsPresenter(mView, dataManager);
    }
}
