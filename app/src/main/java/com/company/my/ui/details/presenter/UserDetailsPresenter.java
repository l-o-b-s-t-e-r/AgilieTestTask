package com.company.my.ui.details.presenter;

import com.company.my.model.manager.IDataManager;
import com.company.my.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Lobster on 30.09.17.
 */

public class UserDetailsPresenter extends BasePresenter<IUserDetailsPresenter.View> implements IUserDetailsPresenter.Actions {

    @Inject
    public UserDetailsPresenter(IUserDetailsPresenter.View view, IDataManager data) {
        super(view, data);
    }

    @Override
    public void getUser(Long id) {
        mView.showUserDetails(mData.getUserById(id, mRealm));
    }
}
