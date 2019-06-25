package com.example.sorpluserend.HomePage.MVP.Market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sorpluserend.HomePage.Model.CartList;
import com.example.sorpluserend.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder>
{
    List<CartList> list;
    Context context;

    public MarketAdapter(List<CartList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.card_market_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketAdapter.ViewHolder viewHolder, int i) {
        CartList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        viewHolder.price.setText("Rs "+cartList.getPrice());
        viewHolder.uses.setText(cartList.getDescription());
        Picasso.get().load(cartList.getImage_url()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,price,uses;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.marketlist_name);
            price=itemView.findViewById(R.id.marketlist_price);
            image=itemView.findViewById(R.id.marketlist_image);
            uses=itemView.findViewById(R.id.marketlist_uses);
            uses.setSelected(true);
        }
    }
}
