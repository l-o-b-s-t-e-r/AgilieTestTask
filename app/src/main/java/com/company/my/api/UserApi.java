package com.company.my.api;

import com.company.my.model.entity.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Lobster on 29.09.17.
 */

public interface UserApi {

    @GET("users")
    Single<List<User>> loadUsers();

}
