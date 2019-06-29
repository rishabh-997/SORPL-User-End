package com.example.sorpluserend.HomePage.MVP.Cart;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sorpluserend.HomePage.Model.CartList;
import com.example.sorpluserend.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    onNoteClickListener onNoteClickListener;
    List<CartList> list;
    Context context;

    public CartAdapter(List<CartList> list, Context context,onNoteClickListener onNoteClickListener) {
        this.list = list;
        this.context = context;
        this.onNoteClickListener=onNoteClickListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_cartlist,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder viewHolder, int i)
    {
        CartList cartList=list.get(i);
        viewHolder.name.setText(cartList.getName());
        Picasso.get().load(cartList.getImage_url()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout linearLayout;
        ImageView image,dustbin;
        TextView name;
        onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView,onNoteClickListener onNoteClickListener) {
            super(itemView);
            listener=onNoteClickListener;
            name=itemView.findViewById(R.id.cart_name);
            image=itemView.findViewById(R.id.cart_image);
            dustbin=itemView.findViewById(R.id.cart_delete);
            linearLayout=itemView.findViewById(R.id.cart_layout);

            dustbin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickdelete(getAdapterPosition());
                }
            });
        }
    }

    public interface onNoteClickListener
    {
        void clickdelete(int pos);
    }
}
