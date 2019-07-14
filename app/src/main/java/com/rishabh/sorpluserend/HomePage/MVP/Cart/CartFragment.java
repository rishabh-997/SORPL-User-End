package com.rishabh.sorpluserend.HomePage.MVP.Cart;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rishabh.sorpluserend.HomePage.Model.CartList;
import com.rishabh.sorpluserend.HomePage.Model.CartResponse;
import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Utilities.SharedPref;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment implements CartContract.view,CartAdapter.onNoteClickListener
{
    CartFragmentListener listener;
    CartContract.presenter presenter;
    SharedPref sharedPref;

    @BindView(R.id.cart_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.asli_cart_bar)
    ProgressBar progressBar;
    @BindView(R.id.cart_send)
    TextView send;

    CartAdapter adapter;
    List<CartList> list=new ArrayList<>();

    public CartFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        presenter=new CartPresenter(this);
        ButterKnife.bind(this,view);
        sharedPref=new SharedPref(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.VISIBLE);
        presenter.getCart(sharedPref.getMobile(),"All");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        return view;
    }

    private void sendRequest()
    {
        if(list.size()==0)
            Toast.makeText(getContext(), "CartActivity is Empty", Toast.LENGTH_SHORT).show();
        else {
            progressBar.setVisibility(View.VISIBLE);
            presenter.send(sharedPref.getAccessToken());
        }
    }

    @Override
    public void clickdelete(final int pos) {

        final AlertDialog alertDialog=new AlertDialog.Builder(getContext()).create();
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
        adapter=new CartAdapter(list,getContext(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void toast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(int pos, String message) {
        presenter.getCart(sharedPref.getMobile(),"All");
    }

    public void RefreshCart()
    {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getCart(sharedPref.getMobile(),"All");
    }

    public interface CartFragmentListener
    {

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof CartFragmentListener)
        {
            listener=(CartFragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()+"must be implemented");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}
