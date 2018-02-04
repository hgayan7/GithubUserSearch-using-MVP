package com.example.him.githubsearch.Presenter;


import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.him.githubsearch.Interface.MainContract;
import com.example.him.githubsearch.Model.RestResponse;
import com.example.him.githubsearch.Rest.ApiClient;
import com.example.him.githubsearch.Rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        Call<RestResponse> call=apiService.getData(userId);
        call.enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {

                RestResponse restResponse = response.body();
                if (response.isSuccessful()) {
                    mvpView.showData(restResponse.getAvatarUrl(), restResponse.getName(),
                            String.valueOf(restResponse.getFollowers()), String.valueOf(restResponse.getPublicRepos()));
                }else {
                    Toast.makeText(view.getContext(), "Enter a valid username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: "+t);

            }
        });

    }
}
