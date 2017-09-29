package com.company.my.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Lobster on 29.09.17.
 */

public interface UserApi {

    @GET("users")
    Observable<Object> loadUsers();

}
