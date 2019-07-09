package com.example.sorpluserend.MyDetails.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.MyDetails.Model.ClientModel;
import com.example.sorpluserend.MyDetails.Model.ResponseClient;
import com.example.sorpluserend.R;
import com.example.sorpluserend.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailContract.view
{
    DetailContract.presenter presenter;
    SharedPref sharedPref;
    List<ClientModel> list=new ArrayList<>();

    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.detail_bar)
    ProgressBar progressBar;
    @BindView(R.id.client_details_name)
    EditText cname;
    @BindView(R.id.client_details_mobile)
    EditText cmobile;
    @BindView(R.id.client_details_phone)
    EditText cphone;
    @BindView(R.id.client_details_email)
    EditText cemail;
    @BindView(R.id.client_details_pan)
    EditText cpan;
    @BindView(R.id.client_details_billto)
    EditText cbillto;
    @BindView(R.id.client_details_shipto)
    EditText cshipto;
    @BindView(R.id.client_details_gst)
    EditText cgst;
    @BindView(R.id.client_details_bankname)
    EditText cbankname;
    @BindView(R.id.client_details_ifsc)
    EditText cifsc;
    @BindView(R.id.client_details_isc)
    EditText cisc;
    @BindView(R.id.client_details_bankphone)
    EditText cbankphone;
    @BindView(R.id.client_details_accno)
    EditText caccno;
    @BindView(R.id.client_details_msme)
    EditText cmsme;
    @BindView(R.id.client_details_transport)
    EditText ctransport;
    @BindView(R.id.client_update)
    Button update;

    String name="",mobile="",phone="phone",email="email",pan="pam",billto="",shipto="",gst="gst";
    String bankname="name",ifsc="ifsc",isc="isc",bankphone="bankpone", accno="accno",msmenumber="msme",preferred="preffered",regtype="client";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);
        presenter=new DetailPreseenter(this);
        getSupportActionBar().hide();

        heading.setText("My Details");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        presenter.search(sharedPref.getMobile());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetails();
            }
        });
    }

    private void updateDetails()
    {
        name=cname.getText().toString().trim();
        mobile=cmobile.getText().toString().trim();
        phone=cphone.getText().toString().trim();
        email=cemail.getText().toString().trim();
        pan=cpan.getText().toString().trim();
        billto=cbillto.getText().toString().trim();
        shipto=cshipto.getText().toString().trim();
        gst=cgst.getText().toString().trim();
        bankname=cbankname.getText().toString().trim();
        ifsc=cifsc.getText().toString().trim();
        isc=cisc.getText().toString().trim();
        bankphone=cbankphone.getText().toString().trim();
        accno=caccno.getText().toString().trim();
        msmenumber=cmsme.getText().toString().trim();
        preferred=ctransport.getText().toString().trim();

        if(name.trim().isEmpty()) {
            cname.setError("Name Cannot be Empty");
            cname.requestFocus();
        }
        else if(mobile.trim().isEmpty()){
            cmobile.setError("Mobile Cannot be Empty");
            cmobile.requestFocus();
        }
        else if(billto.trim().isEmpty()){
            cbillto.setError("Billing Address cannot be Empty");
            cbillto.requestFocus();
        }
        else if(shipto.trim().isEmpty()){
            cshipto.setError("Shipping Address Cannot be Empty");
            cshipto.requestFocus();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            presenter.update(name,mobile,phone,email,pan,billto,shipto,gst,bankname,ifsc,isc,bankphone,accno,msmenumber,preferred,regtype);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void showToast(String message)
    {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(ResponseClient body)
    {
        progressBar.setVisibility(View.GONE);
        list=body.getList();

        ClientModel clientModel=list.get(0);

        cname.setText(clientModel.getName());
        cmobile.setText(clientModel.getMobile());
        cbillto.setText(clientModel.getBillTo());
        cshipto.setText(clientModel.getShipTo());

        cphone.setText(clientModel.getPhone());
        cemail.setText(clientModel.getEmail());
        cpan.setText(clientModel.getPan());
        cgst.setText(clientModel.getGSTNo());
        cbankname.setText(clientModel.getBankName());
        cbankphone.setText(clientModel.getBankPhone());
        cifsc.setText(clientModel.getIFSCNo());
        cisc.setText(clientModel.getISCCode());
        caccno.setText(clientModel.getAccountNo());
        cmsme.setText(clientModel.getMSMENo());
        ctransport.setText(clientModel.getTransportNo());
    }
}
