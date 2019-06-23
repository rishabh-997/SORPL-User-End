package com.example.sorpluserend.SignUp.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.LogIn.MVP.LogInActivity;
import com.example.sorpluserend.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.view
{
    SignUpContract.presenter presenter;
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_company)
    EditText _addressText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        presenter=new SignUpPresenter(this);
        ButterKnife.bind(this);

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
            }
        });

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login()
    {
        if(_nameText.getText().toString().isEmpty())
        {
            _nameText.setError("Enter Name");
            _nameText.requestFocus();
        }
        if(_mobileText.getText().toString().isEmpty())
        {
            _mobileText.setError("Enter Mobile");
            _mobileText.requestFocus();
        }
        if(_emailText.getText().toString().isEmpty())
        {
            _emailText.setError("Enter Email ID");
            _emailText.requestFocus();
        }
        if(_addressText.getText().toString().isEmpty())
        {
            _addressText.setError("Enter Comapny");
            _addressText.requestFocus();
        }

        if(!(_nameText.getText().toString().isEmpty())&&!(_mobileText.getText().toString().isEmpty())&&!(_emailText.getText().toString().isEmpty())&&!(_addressText.getText().toString().isEmpty()))
        {
            presenter.createUser(_nameText.getText().toString(),_mobileText.getText().toString(),_emailText.getText().toString(),_addressText.getText().toString(),"client");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success()
    {
        Toast.makeText(this, "SuccessFully Created Account !\nPlease LogIn to continue", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_out, R.anim.push_left_in);
    }
}
