package com.example.sorpluserend.CompanyData.MVP;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorpluserend.CompanyData.Model.DataList;
import com.example.sorpluserend.CompanyData.Model.DataResponse;
import com.example.sorpluserend.CompanyData.ViewActivity;
import com.example.sorpluserend.R;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyActivity extends AppCompatActivity implements CompanyContract.view,CompanyAdapter.onNoteClickListener
{
    private static final int MY_PERMISSION_REQUEST_CODE = 6969;
    CompanyContract.presenter presenter;
    CompanyAdapter adapter;
    List<DataList> list=new ArrayList<>();

    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar_back)
    ImageView back;
    @BindView(R.id.data_bar)
    ProgressBar progressBar;
    @BindView(R.id.data_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        presenter=new CompanyPresenter(this);

        toolbarText.setText("Company Data");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);
        presenter.getPDF();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(DataResponse body)
    {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getDataList();

        adapter=new CompanyAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position)
    {
        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermission();
        }
        else
        {
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("url", list.get(position).getUrl());
            startActivity(intent);
        }
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_CODE);
    }
}
