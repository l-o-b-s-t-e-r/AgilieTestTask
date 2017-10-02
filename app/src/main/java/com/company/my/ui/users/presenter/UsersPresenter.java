package com.company.my.ui.users.presenter;

import com.company.my.R;
import com.company.my.app.App;
import com.company.my.model.entity.User;
import com.company.my.model.manager.IDataManager;
import com.company.my.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by Lobster on 30.09.17.
 */

public class UsersPresenter extends BasePresenter<IUsersPresenter.View> implements IUsersPresenter.Actions {

    @Inject
    public UsersPresenter(IUsersPresenter.View view, IDataManager data) {
        super(view, data);
    }

    @Override
    public void loadUsers() {
        addDisposable(
                mData.loadUsers()
                        .doOnError(throwable -> {
                            mView.showToast(App.getInstance().getString(R.string.error_network));
                            getCachedUsers();
                        })
                        .flatMap(new Function<List<User>, SingleSource<List<User>>>() {
                            @Override
                            public SingleSource<List<User>> apply(List<User> users) throws Exception {
                                Observable.fromIterable(users)
                                        .forEach(user -> user.setAvatarUrl(String.format(App.getInstance().getString(R.string.avatar_url), user.getId())));

                                return Single.create((SingleOnSubscribe<List<User>>) e -> {
                                    mData.saveUsers(users, mRealm);
                                    e.onSuccess(mData.getUsers(mRealm));
                                }).doOnError(throwable -> mView.showToast(App.getInstance().getString(R.string.error_caching)));
                            }
                        })
                        .subscribe(
                                users -> mView.showUsers(users),
                                throwable -> mView.printError(throwable))
        );
    }

    @Override
    public void getCachedUsers() {
        if (mData.checkIfUsersExist(mRealm)) {
            mView.showUsers(mData.getUsers(mRealm));
        } else {
            mView.showToast(App.getInstance().getString(R.string.error_empty_cache));
        }
    }
}
