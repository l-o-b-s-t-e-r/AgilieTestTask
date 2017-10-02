package com.company.my.ui.details.presenter;

import com.company.my.model.entity.User;
import com.company.my.ui.base.IBasePresenter;

/**
 * Created by Lobster on 30.09.17.
 */

public interface IUserDetailsPresenter {

    interface Actions extends IBasePresenter.Actions {

        void getUser(Long id);

    }

    interface View extends IBasePresenter.View {

        void showUserDetails(User user);

    }

}
