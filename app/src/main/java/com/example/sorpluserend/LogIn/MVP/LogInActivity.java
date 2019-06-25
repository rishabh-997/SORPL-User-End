package com.example.sorpluserend.LogIn.MVP;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.MVP.HomeActivity;
import com.example.sorpluserend.OTP.MVP.OTPActivity;
import com.example.sorpluserend.R;
import com.example.sorpluserend.SignUp.MVP.SignUpActivity;
import com.example.sorpluserend.Utilities.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInContract.view
{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.login_bar)
    ProgressBar progressBar;
    LogInContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter=new LogInPresenter(this);
        ButterKnife.bind(this);

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login()
    {
        if(_emailText.getText().toString().isEmpty()||_emailText.getText().toString().length()!=10)
        {
            _emailText.setError("Enter Valid Mobile");
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            String fcm= MyApplication.getFcm();
            String mobile=_emailText.getText().toString();

            presenter.dologin(mobile,fcm);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OTPPage() {

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
        intent.putExtra("Mobile",_emailText.getText().toString());
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
