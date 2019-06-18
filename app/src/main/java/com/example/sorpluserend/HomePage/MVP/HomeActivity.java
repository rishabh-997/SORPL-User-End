package com.example.sorpluserend.HomePage.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.LogIn.MVP.LogInContract;
import com.example.sorpluserend.LogIn.MVP.LogInPresenter;
import com.example.sorpluserend.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.view
{
    HomeContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter=new HomePresenter(this);

        startActivity(new Intent(this, LogInActivity.class));
    }
}
