package com.example.sorpluserend.HomePage.MVP.Market;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sorpluserend.HomePage.Model.CartList;
import com.example.sorpluserend.HomePage.Model.CartResponse;
import com.example.sorpluserend.HomePage.Model.Comapany_list;
import com.example.sorpluserend.HomePage.Model.Comapny_response;
import com.example.sorpluserend.HomePage.Model.MarketList;
import com.example.sorpluserend.HomePage.Model.MarketResponse;
import com.example.sorpluserend.HomePage.Model.SubCat_list;
import com.example.sorpluserend.HomePage.Model.SubCat_response;
import com.example.sorpluserend.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketFragment extends Fragment implements MarketContract.view
{
    MarketContract.presenter presenter;
    MarketAdapter adapter;
    List<MarketList> list=new ArrayList<>();

    @BindView(R.id.market_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.market_bar)
    ProgressBar progressBar;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipe;


    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_market, container, false);
        presenter=new MarketPresenter(this);
        ButterKnife.bind(this,view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar.setVisibility(View.VISIBLE);

        presenter.getList();

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
        presenter.getList();
    }

    @Override
    public void toast(String message)
    {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(MarketResponse body) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getList();
        adapter=new MarketAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    }
}
