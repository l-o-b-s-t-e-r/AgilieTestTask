package com.company.my.model.manager;

import com.company.my.api.UserApi;
import com.company.my.model.entity.User;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Lobster on 29.09.17.
 */

public class DataManager implements IDataManager {

    private UserApi mUserApi;

    public DataManager(UserApi userApi) {
        this.mUserApi = userApi;
    }

    @Override
    public Single<List<User>> loadUsers() {
        return mUserApi.loadUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveUsers(List<User> users, Realm realm) throws Exception {
        realm.executeTransaction(transactionRealm -> transactionRealm.copyToRealmOrUpdate(users));
    }

    @Override
    public List<User> getUsers(Realm realm) {
        return realm.where(User.class).findAllSorted("name");
    }

    @Override
    public User getUserById(Long id, Realm realm) {
        return realm.where(User.class)
                .equalTo("id", id)
                .findFirst();
    }

    @Override
    public boolean checkIfUsersExist(Realm realm) {
        return realm.where(User.class).count() > 0;
    }
}
