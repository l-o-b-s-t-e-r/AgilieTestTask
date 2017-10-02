package com.company.my.model.manager;

import com.company.my.model.entity.User;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by Lobster on 29.09.17.
 */

public interface IDataManager {

    Single<List<User>> loadUsers();

    void saveUsers(List<User> users, Realm realm) throws Exception;

    List<User> getUsers(Realm realm);

    User getUserById(Long id, Realm realm);

    boolean checkIfUsersExist(Realm realm);

}
