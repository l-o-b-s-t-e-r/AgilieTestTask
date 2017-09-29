package com.company.my.ui.base;

import com.company.my.model.manager.IDataManager;

import io.realm.Realm;

/**
 * Created by Lobster on 29.09.17.
 */

public class BasePresenter<T extends IBasePresenter.View> implements IBasePresenter.Actions {

    protected T mView;
    protected IDataManager mData;
    private Realm mRealm;

    public BasePresenter(T view, IDataManager data) {
        mView = view;
        mData = data;
    }

    @Override
    public void setRealm(Realm realm) {
        mRealm = realm;
    }

    public Realm getRealm() {
        return mRealm;
    }

}
