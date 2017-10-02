package com.company.my.ui.users.presenter;

import com.company.my.model.entity.User;
import com.company.my.ui.base.IBasePresenter;

import java.util.List;

/**
 * Created by Lobster on 30.09.17.
 */

public interface IUsersPresenter {

    interface Actions extends IBasePresenter.Actions {

        void loadUsers();

        void getCachedUsers();

    }

    interface View extends IBasePresenter.View {

        void showUsers(List<User> users);

    }

}
