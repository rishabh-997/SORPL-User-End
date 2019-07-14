package com.rishabh.sorpluserend.Cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rishabh.sorpluserend.ContactUs.MVP.ContactActivity;
import com.rishabh.sorpluserend.HomePage.Model.CartList;
import com.rishabh.sorpluserend.HomePage.Model.CartResponse;
import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartContract_real.view,CartAdapter_real.onNoteClickListener
{
    CartContract_real.presenter presenter;
    SharedPref sharedPref;
    android.os.Handler Handler = new Handler();

    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;
    @BindView(R.id.cart_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.asli_cart_bar)
    ProgressBar progressBar;
    @BindView(R.id.cart_send)
    TextView send;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipe;


    CartAdapter_real adapter;
    List<CartList> list=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);
        ButterKnife.bind(this);
        presenter=new CartPresenter_real(this);
        sharedPref=new SharedPref(this);

        getSupportActionBar().hide();
        heading.setText("Cart");
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
        presenter.getCart(sharedPref.getMobile(),"All");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

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
        presenter.getCart(sharedPref.getMobile(),"All");
    }

    private void sendRequest()
    {
        if(list.size()==0)
            Toast.makeText(this, "CartActivity is Empty", Toast.LENGTH_SHORT).show();
        else {
            progressBar.setVisibility(View.VISIBLE);
            presenter.send(sharedPref.getAccessToken());
        }
    }

    @Override
    public void clickdelete(final int pos) {

        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Add Product To CartActivity");
        alertDialog.setTitle("Are You Sure ?");
        alertDialog.setCancelable(false);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);
                presenter.deleteCart(list.get(pos).getCartid(),pos);
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    @Override
    public void showCart(CartResponse body) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getList();
        Collections.reverse(list);
        adapter=new CartAdapter_real(list,this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void toast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(int pos, String message) {
        presenter.getCart(sharedPref.getMobile(),"All");
    }

    @Override
    public void exithere() {

        Toast.makeText(this, "Your Enquiry has been recieved. You can Contact us to know the status of your Enquiry", Toast.LENGTH_LONG).show();

        Handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent intent=new Intent(CartActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        },1000);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
