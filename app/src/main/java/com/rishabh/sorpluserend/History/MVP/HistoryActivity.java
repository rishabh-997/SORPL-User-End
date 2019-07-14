package com.rishabh.sorpluserend.History.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.rishabh.sorpluserend.History.Model.HistoryDetailList;
import com.rishabh.sorpluserend.History.Model.HistoryDetailResponse;
import com.rishabh.sorpluserend.History.Model.HistoryList;
import com.rishabh.sorpluserend.History.Model.HistoryResponse;
import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.view,HistoryAdapter.onNoteClickListener
{
    HistoryContract.presenter presenter;
    SharedPref sharedPref;

    @BindView(R.id.history_name)
    TextView name;
    @BindView(R.id.history_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.history_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipe;
    @BindView(R.id.empty_layout)
    RelativeLayout relativeLayout;

    List<HistoryList> list=new ArrayList<>();
    HistoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        sharedPref=new SharedPref(this);

        getSupportActionBar().hide();

        heading.setText("History");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        presenter=new HistoryPresenter(this);
        presenter.getHistory(sharedPref.getMobile(),"Client", "All");

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe.setRefreshing(false);
            }
        });
    }

    private void refresh()
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getHistory(sharedPref.getMobile(),"Client", "All");
    }

    @Override
    public void showToast(String message)
    {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(HistoryResponse body)
    {
        progressBar.setVisibility(View.GONE);
        list=body.getHistoryList();

        if(list.size()==0)
            relativeLayout.setVisibility(View.VISIBLE);
        else {
            relativeLayout.setVisibility(View.GONE);
            Collections.reverse(list);
            adapter = new HistoryAdapter(this, list, this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showDetails(HistoryDetailResponse body, String id, String comment)
    {
        progressBar.setVisibility(View.GONE);
        List<HistoryDetailList> detailList=body.getHistoryDetailList();
        if(comment.trim().isEmpty())
            comment="No Comment Found";

        String title=id;
        String message="Additional Info. \n"+comment+"\n\n";

        for(int i=0;i<detailList.size();i++)
        {
            String one="Product : "+detailList.get(i).getId()+"\n"+"Unit : "+detailList.get(i).getUnit()+"\n"+"Number of Units : "+detailList.get(i).getSize()+"\n"+"Cost per item : "+detailList.get(i).getCost()+"\n"+"Total Cost : "+detailList.get(i).getTotal_cost();
            message=message+one+"\n\n";
        }


        message.trim();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onNoteClick(int position) {
        String orderid=list.get(position).getOrderId();
        String comment=list.get(position).getComment();
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDetails(orderid,comment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
