package com.example.sorpluserend.CompanyData.MVP;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sorpluserend.CompanyData.Model.DataList;
import com.example.sorpluserend.CompanyData.ViewActivity;
import com.example.sorpluserend.R;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder>
{
    Context context;
    List<DataList> list;
    onNoteClickListener onNoteClickListener;

    public CompanyAdapter(Context context, List<DataList> list, CompanyAdapter.onNoteClickListener onNoteClickListener) {
        this.context = context;
        this.list = list;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_pdf,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder viewHolder, int i) {
        DataList dataList=list.get(i);

        if(i%2==1) {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ECEAF5"));
        }
        else
        {
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        viewHolder.name.setText(dataList.getName());
        viewHolder.desc.setText(dataList.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        onNoteClickListener listener;
        TextView name,desc;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView,onNoteClickListener onNoteClickListener) {
            super(itemView);
            listener=onNoteClickListener;
            name=itemView.findViewById(R.id.pdf_Name);
            desc=itemView.findViewById(R.id.pdf_desc);
            linearLayout=itemView.findViewById(R.id.layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(getAdapterPosition());
                }
            });
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(getAdapterPosition());
                }
            });
            desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(getAdapterPosition());
                }
            });
        }
    }

    public interface onNoteClickListener
    {
        void onNoteClick(int position);
    }
}
