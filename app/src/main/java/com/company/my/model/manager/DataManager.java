package com.company.my.model.manager;

import com.company.my.api.AvatarApi;
import com.company.my.api.UserApi;

/**
 * Created by Lobster on 29.09.17.
 */

public class DataManager implements IDataManager {

    private UserApi mUserApi;
    private AvatarApi mAvatarApi;

    public DataManager(UserApi userApi, AvatarApi avatarApi) {
        this.mUserApi = userApi;
        this.mAvatarApi = avatarApi;
    }
}
