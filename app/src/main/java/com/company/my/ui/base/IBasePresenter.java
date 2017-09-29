package com.company.my.ui.base;

import io.realm.Realm;

/**
 * Created by Lobster on 29.09.17.
 */

public interface IBasePresenter {

    interface Actions {

        void setRealm(Realm realm);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void showToast(String message);

    }

}
