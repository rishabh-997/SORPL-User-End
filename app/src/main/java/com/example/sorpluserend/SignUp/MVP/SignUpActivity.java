package com.example.sorpluserend.SignUp.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sorpluserend.R;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.view
{
    SignUpContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        presenter=new SignUpPresenter(this);
    }
}
