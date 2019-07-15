package com.rishabh.sorpluserend.HomePage.MVP.Feed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationList;
import com.rishabh.sorpluserend.Affiliation.Model.AffiliationResponse;
import com.rishabh.sorpluserend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment implements FeedContract.view
{
    FeedContract.presenter presenter;
    FeedAdapter adapter;
    List<AffiliationList> list=new ArrayList<>();

    @BindView(R.id.feed_bar)
    ProgressBar progressBar;
    @BindView(R.id.feed_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshfeed)
    SwipeRefreshLayout swipe;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        presenter=new FeedPresenter(this);
        ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);
        presenter.getFeeds();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipe.setRefreshing(false);
            }
        });

        return view;
    }

    private void refresh()
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getFeeds();
    }

    @Override
    public void showList(AffiliationResponse body) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getAffiliationList();
        adapter=new FeedAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void toast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
