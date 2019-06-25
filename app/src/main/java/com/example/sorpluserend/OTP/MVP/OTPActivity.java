package com.example.sorpluserend.OTP.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.MVP.HomeActivity;
import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.LogIn.Model.LogInResponse;
import com.example.sorpluserend.OTP.Model.OTPResponse;
import com.example.sorpluserend.R;
import com.example.sorpluserend.SignUp.MVP.SignUpActivity;
import com.example.sorpluserend.Utilities.MyApplication;
import com.example.sorpluserend.Utilities.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTPActivity extends AppCompatActivity implements OTPContract.view
{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.otp_again)
    TextView again;
    @BindView(R.id.otp_login)
    Button login;
    @BindView(R.id.otp_mobile)
    EditText otp;
    @BindView(R.id.otp_bar)
    ProgressBar progressBar;

    OTPContract.presenter presenter;
    String mobile;
    SharedPref sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Intent intent=getIntent();
        mobile=intent.getStringExtra("Mobile");
        sharedPref=new SharedPref(this);

        presenter=new OTPPresenter(this);
        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP();
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private void verifyOTP()
    {
        if(otp.getText().toString().isEmpty())
        {
            otp.setError("Enter Valid OTP !");
            otp.requestFocus();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            presenter.verify(mobile,otp.getText().toString());
        }
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterapp(OTPResponse body)
    {
        progressBar.setVisibility(View.GONE);
        sharedPref.setAccessToken(body.getAccess_token());
        sharedPref.setMobile(mobile);

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }
}
