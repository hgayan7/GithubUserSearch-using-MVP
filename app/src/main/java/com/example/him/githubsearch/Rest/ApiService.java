package com.example.him.githubsearch.Rest;

import com.example.him.githubsearch.Model.RestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by him on 2/4/2018.
 */

public interface ApiService {

    @GET("users/{login}")
    Observable<RestResponse> getData(@Path("login")String userId);

}
