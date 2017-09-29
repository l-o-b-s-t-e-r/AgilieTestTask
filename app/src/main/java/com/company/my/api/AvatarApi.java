package com.company.my.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lobster on 29.09.17.
 */

public interface AvatarApi {

    @GET("twitter/{user_id}")
    Observable<Object> loadAvatars(@Path("user_id") Long id);

}
