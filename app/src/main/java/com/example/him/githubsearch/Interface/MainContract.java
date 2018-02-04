package com.example.him.githubsearch.Interface;

import android.view.View;

/**
 * Created by him on 2/4/2018.
 */

public interface MainContract {

    interface mvpView{

        void showData(String url,String name,String followers,String repos);
    }

    interface mvpPresenter{

        void loadData(View view,String id);
    }
}
