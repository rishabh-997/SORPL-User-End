package com.example.sorpluserend.HomePage.MVP.Product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sorpluserend.HomePage.Model.ProductList;
import com.example.sorpluserend.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    onNoteClickListener onNoteClickListener;
    ProductList productList;
    List<ProductList> list;
    Context context;

    public ProductAdapter(List<ProductList> list, Context context,onNoteClickListener onNoteClickListener) {
        this.onNoteClickListener=onNoteClickListener;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_prod_list,viewGroup,false);
        return new ViewHolder(view,onNoteClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder viewHolder, int i) {
        productList=list.get(i);
        String image_url=productList.getImage_url();
        viewHolder.name.setText(productList.getName());
        viewHolder.description.setText("Uses : \n"+productList.getDescription());

        if(image_url.isEmpty())
            image_url="http://unbxd.com/blog/wp-content/uploads/2014/02/No-results-found.jpg";
        Picasso.get().load(image_url).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,description;
        ImageView imageView;
        ImageView specs;
        onNoteClickListener listener;

        public ViewHolder(@NonNull View itemView,onNoteClickListener onNoteClickListener)
        {
            super(itemView);
            listener=onNoteClickListener;
            name=itemView.findViewById(R.id.prodlist_name);
            description=itemView.findViewById(R.id.prodlist_description);
            imageView=itemView.findViewById(R.id.prodlist_image);
            specs=itemView.findViewById(R.id.prodlist_specs);

            specs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface onNoteClickListener
    {
        void onNoteClick(int position);
    }
}
