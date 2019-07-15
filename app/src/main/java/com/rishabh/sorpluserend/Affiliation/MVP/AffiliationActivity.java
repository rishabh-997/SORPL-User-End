package com.rishabh.sorpluserend.Affiliation.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationList;
import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;
import com.rishabh.sorpluserend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AffiliationActivity extends AppCompatActivity implements AffiliationContract.view
{
    AffiliationContract.presenter presenter;
    AffiliationAdapter adapter;
    List<AffiliationList> list=new ArrayList<>();

    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.affili_bar)
    ProgressBar progressBar;
    @BindView(R.id.affili_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliation);
        ButterKnife.bind(this);
        presenter=new AffiliationPresenter(this);
        getSupportActionBar().hide();

        heading.setText("Affiliation");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        presenter.getList();
    }

    @Override
    public void showList(AffiliationResponse body)
    {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getAffiliationList();
        adapter=new AffiliationAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void toast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
