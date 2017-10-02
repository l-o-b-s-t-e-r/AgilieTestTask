package com.company.my.ui.users.di;

import com.company.my.app.di.FragmentScope;
import com.company.my.model.manager.IDataManager;
import com.company.my.ui.users.presenter.IUsersPresenter;
import com.company.my.ui.users.presenter.UsersPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lobster on 29.09.17.
 */

@Module
public class UsersModule {

    private IUsersPresenter.View mView;

    public UsersModule(IUsersPresenter.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    public UsersPresenter providePresenter(IDataManager dataManager) {
        return new UsersPresenter(mView, dataManager);
    }
}
