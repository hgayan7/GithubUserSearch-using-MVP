package com.example.him.githubsearch.Presenter;


import android.util.Log;
import android.view.View;
import com.example.him.githubsearch.Interface.MainContract;
import com.example.him.githubsearch.Model.RestResponse;
import com.example.him.githubsearch.Rest.ApiClient;
import com.example.him.githubsearch.Rest.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import static android.content.ContentValues.TAG;

/**
 * Created by him on 2/4/2018.
 */

public class MainPresenter implements MainContract.mvpPresenter{

    private final MainContract.mvpView mvpView;
    private  ApiService apiService;

    public MainPresenter(MainContract.mvpView view){

        this.mvpView=view;
        this.apiService=ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void loadData(final View view, String userId) {

        apiService.getData(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult,this::handleError);

    }

    private void handleResult(RestResponse restResponse){

        if(restResponse!=null){
            mvpView.showData(restResponse.getAvatarUrl(), restResponse.getName(),
                    String.valueOf(restResponse.getFollowers()), String.valueOf(restResponse.getPublicRepos()));
        }else {
            Log.d(TAG, "handleResult: "+"Enter a valid username");
        }
    }

    private void handleError(Throwable throwable){

        Log.d(TAG, "handleError: "+throwable);
    }
}
