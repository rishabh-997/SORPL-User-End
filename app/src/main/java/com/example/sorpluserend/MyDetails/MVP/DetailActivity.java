package com.example.sorpluserend.MyDetails.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    TextView name;
    @BindView(R.id.client_details_mobile)
    TextView mobile;
    @BindView(R.id.client_details_phone)
    TextView phone;
    @BindView(R.id.client_details_email)
    TextView email;
    @BindView(R.id.client_details_pan)
    TextView pan;
    @BindView(R.id.client_details_billto)
    TextView billto;
    @BindView(R.id.client_details_shipto)
    TextView shipto;
    @BindView(R.id.client_details_gst)
    TextView gst;
    @BindView(R.id.client_details_bankname)
    TextView bankname;
    @BindView(R.id.client_details_ifsc)
    TextView ifsc;
    @BindView(R.id.client_details_isc)
    TextView isc;
    @BindView(R.id.client_details_bankphone)
    TextView bankphone;
    @BindView(R.id.client_details_accno)
    TextView accno;
    @BindView(R.id.client_details_msme)
    TextView msme;
    @BindView(R.id.client_details_transport)
    TextView transport;

    String blank="Not Available";

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
                finish();
                onBackPressed();
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        presenter.search(sharedPref.getMobile());
    }

    @Override
    public void onBackPressed() {
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

        name.setText(clientModel.getName());
        mobile.setText(clientModel.getMobile());
        billto.setText(clientModel.getBillTo());
        shipto.setText(clientModel.getShipTo());

        phone.setText(clientModel.getPhone());
        email.setText(clientModel.getEmail());
        pan.setText(clientModel.getPan());
        gst.setText(clientModel.getGSTNo());
        bankname.setText(clientModel.getBankName());
        bankphone.setText(clientModel.getBankPhone());
        ifsc.setText(clientModel.getIFSCNo());
        isc.setText(clientModel.getISCCode());
        accno.setText(clientModel.getAccountNo());
        msme.setText(clientModel.getMSMENo());
        transport.setText(clientModel.getTransportNo());
    }
}
