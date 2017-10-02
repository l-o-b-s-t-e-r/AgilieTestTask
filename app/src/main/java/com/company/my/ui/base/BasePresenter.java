package com.company.my.ui.base;

import com.company.my.model.manager.IDataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * Created by Lobster on 29.09.17.
 */

public class BasePresenter<T extends IBasePresenter.View> implements IBasePresenter.Actions {

    protected T mView;
    protected IDataManager mData;
    protected Realm mRealm;
    private CompositeDisposable mDisposables = new CompositeDisposable();

    public BasePresenter(T view, IDataManager data) {
        mView = view;
        mData = data;
    }

    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public void clear() {
        mDisposables.clear();
    }

    @Override
    public void setRealm(Realm realm) {
        mRealm = realm;
    }

}
