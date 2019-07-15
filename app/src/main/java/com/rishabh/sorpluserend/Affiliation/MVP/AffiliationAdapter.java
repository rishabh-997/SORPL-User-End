package com.rishabh.sorpluserend.Affiliation.MVP;

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

public class AffiliationAdapter extends RecyclerView.Adapter<AffiliationAdapter.ViewHolder> {

    List<AffiliationList> list;
    Context context;

    public AffiliationAdapter(List<AffiliationList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_affiliation,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AffiliationList affiliationList=list.get(i);

        if(i%2==0) {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ECEAF5"));
        }
        else
        {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        viewHolder.name.setText(affiliationList.getName());
        viewHolder.desc.setText(affiliationList.getDescription());
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
        TextView name,desc;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.aff_name);
            desc=itemView.findViewById(R.id.aff_desc);
            imageView=itemView.findViewById(R.id.aff_image);
            linearLayout=itemView.findViewById(R.id.affil_layout);
        }
    }
}
