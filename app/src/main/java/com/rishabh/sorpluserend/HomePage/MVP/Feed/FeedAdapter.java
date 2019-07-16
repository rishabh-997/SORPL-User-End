package com.rishabh.sorpluserend.HomePage.MVP.Feed;

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

import com.rishabh.sorpluserend.Affiliation.Model.AffiliationList;
import com.rishabh.sorpluserend.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    List<AffiliationList> list;
    Context context;

    public FeedAdapter(List<AffiliationList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_feed,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder viewHolder, int i) {
        AffiliationList affiliationList=list.get(i);

        if(i%2==1) {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ECEAF5"));
        }
        else
        {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        viewHolder.desc.setText(affiliationList.getDescription());
        viewHolder.date.setText(affiliationList.getDate());
        Picasso.get()
                .load(affiliationList.getUrl())
                .resizeDimen(R.dimen.resize_image,R.dimen.resize_image)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView desc,date;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.feed_desc);
            imageView=itemView.findViewById(R.id.feed_image);
            date=itemView.findViewById(R.id.feed_date);
            linearLayout=itemView.findViewById(R.id.feed_layout);
        }
    }
}
