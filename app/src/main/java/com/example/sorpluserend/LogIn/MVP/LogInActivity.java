package com.example.sorpluserend.LogIn.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sorpluserend.R;

public class LogInActivity extends AppCompatActivity implements LogInContract.view
{
    LogInContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter=new LogInPresenter(this);
    }
}
