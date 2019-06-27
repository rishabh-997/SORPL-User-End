package com.example.sorpluserend.ContactUs.MVP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.ContactUs.Model.ContactResponse;
import com.example.sorpluserend.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements ContactContract.view
{
    ContactContract.presenter presenter;

    @BindView(R.id.contactemailtext)
    TextView email_text;
    @BindView(R.id.contact_mobile_text)
    TextView mobile_text;
    @BindView(R.id.contact_phone_text)
    TextView phone_text;
    @BindView(R.id.contactemailbutton)
    Button email_button;
    @BindView(R.id.contact_mobile_button)
    Button mobile_button;
    @BindView(R.id.contact_phone_button)
    Button phone_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        presenter=new ContactPresenter(this);

        presenter.getContact();

        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        mobile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callmobile();
            }
        });
        phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
    }

    private void sendEmail()
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        String mailTo = "mailto:".concat(email_text.getText().toString());
        i.setData(Uri.parse(mailTo));
        try {
            startActivity(i);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void callmobile()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(mobile_text.getText().toString()), null));
        startActivity(intent);
    }
    public void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(phone_text.getText().toString()), null));
        startActivity(intent);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContact(ContactResponse body)
    {
        String email=body.getEmail();
        String mobile=body.getMobile();
        String phone=body.getWork();

        email_text.setText(email);
        mobile_text.setText(mobile);
        phone_text.setText(phone);
    }
}
