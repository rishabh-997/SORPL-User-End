package com.example.sorpluserend.HomePage.MVP.Enquiry;


import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sorpluserend.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryFragment extends Fragment implements EnquiryContract.view
{

    EnquiryContract.presenter presenter;

    @BindView(R.id.enquiryname)
    EditText name;
    @BindView(R.id.enquirymobile)
    EditText mobile;
    @BindView(R.id.enquirycompany)
    EditText cppany;
    @BindView(R.id.enquiryemail)
    EditText email;
    @BindView(R.id.enquirymessage)
    EditText message;
    @BindView(R.id.enquirybutton)
    Button send;


    public EnquiryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_enquiry, container, false);
        ButterKnife.bind(this,view);
        presenter=new EnquiryPresenter(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });


        return view;
    }

    private void sendMail()
    {
        String Name=name.getText().toString();
        String Mobile=mobile.getText().toString();
        String Company=cppany.getText().toString();
        String Email=email.getText().toString();
        String Message=message.getText().toString();

        if(Name.isEmpty())
        {
            name.setError("Enter Name");
            name.requestFocus();
        }
        else if(Mobile.isEmpty())
        {
            mobile.setError("Enter Valid Number");
            mobile.requestFocus();
        }
        else if(Company.isEmpty())
        {
            cppany.setError("Enter Company Name");
            cppany.requestFocus();
        }
        else if(Email.isEmpty())
        {
            email.setError("Enter Email");
            email.requestFocus();
        }
        else if(Message.isEmpty())
        {
            message.setError("Enter Enquiry");
            message.requestFocus();
        }
        else
        {
                presenter.sendMail(Name, Mobile, Company, Email, Message);
        }

    }

    @Override
    public void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
