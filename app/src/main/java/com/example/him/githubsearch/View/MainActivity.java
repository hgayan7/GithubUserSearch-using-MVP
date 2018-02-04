package com.example.him.githubsearch.View;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.him.githubsearch.Interface.MainContract;
import com.example.him.githubsearch.Presenter.MainPresenter;
import com.example.him.githubsearch.R;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainContract.mvpView{

    MainPresenter mainPresenter;
    TextView name,followers,repo;
    CircleImageView circleImageView;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter=new MainPresenter(this);
        setUI();
    }

    public void setUI(){

    name=findViewById(R.id.nameText);
    followers=findViewById(R.id.followersCount);
    repo=findViewById(R.id.publicRepoCount);
    circleImageView=findViewById(R.id.profileImage);
    username=findViewById(R.id.userName);

    }

    @Override
    public void showData(String u,String n,String f,String r) {

        Picasso.with(this).load(u+".jpg").into(circleImageView);
        name.setText(n);
        followers.setText(f);
        repo.setText(r);
        Toast.makeText(this, "Loading complete", Toast.LENGTH_SHORT).show();

    }
    public void load(View view){

        mainPresenter.loadData(view,username.getText().toString());

    }

}
