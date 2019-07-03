package com.example.sorpluserend.HomePage.MVP.Market;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.sorpluserend.HomePage.Model.CartList;
import com.example.sorpluserend.HomePage.Model.MarketList;
import com.example.sorpluserend.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder>
{
    List<MarketList> list;
    Context context;

    public MarketAdapter(List<MarketList> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.card_market_color1, viewGroup, false);
            return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MarketAdapter.ViewHolder viewHolder, int i)
    {
        MarketList marketList=list.get(i);
        if(i%2==1) {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ECEAF5"));
        }
        else
        {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        viewHolder.name.setText(marketList.getName());
        viewHolder.usd.setText(marketList.getUsd()+" USD");
        viewHolder.inr.setText(marketList.getInr()+" INR");
        viewHolder.desc.setText(marketList.getDesc());
        Picasso.get().load(marketList.getImage_url()).into(viewHolder.image);

        if(marketList.getTag().equals("Increase"))
        {
            //green
            viewHolder.usddrop.setText("+"+marketList.getPercent()+"%");
            viewHolder.inrdrop.setText("+"+marketList.getPercent()+"%");
            viewHolder.inrdrop.setTextColor(Color.parseColor("#05AA5A"));
            viewHolder.usddrop.setTextColor(Color.parseColor("#05AA5A"));

        }
        else if(marketList.getTag().equals("Decrease"))
        {
            //red
            viewHolder.usddrop.setText("-"+marketList.getPercent()+"%");
            viewHolder.inrdrop.setText("-"+marketList.getPercent()+"%");
            viewHolder.inrdrop.setTextColor(Color.parseColor("#AF0202"));
            viewHolder.usddrop.setTextColor(Color.parseColor("#AF0202"));
        }
        else
        {
            viewHolder.usddrop.setText(marketList.getPercent()+"%");
            viewHolder.inrdrop.setText(marketList.getPercent()+"%");
            viewHolder.inrdrop.setTextColor(Color.parseColor("#A09DB0"));
            viewHolder.usddrop.setTextColor(Color.parseColor("#A09DB0"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,desc,inr,usd,inrdrop,usddrop;
        ImageView image;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout=itemView.findViewById(R.id.market_layout);
            image=itemView.findViewById(R.id.market_image1);
            name=itemView.findViewById(R.id.market_name1);
            desc=itemView.findViewById(R.id.market_desc1);
            inr=itemView.findViewById(R.id.market_price_inr1);
            usd=itemView.findViewById(R.id.market_price_usd1);
            inrdrop=itemView.findViewById(R.id.market_change_inr1);
            usddrop=itemView.findViewById(R.id.market_change_usd1);
        }
    }
}
