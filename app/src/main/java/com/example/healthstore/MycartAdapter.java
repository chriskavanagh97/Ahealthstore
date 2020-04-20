package com.example.healthstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MycartAdapter  extends RecyclerView.Adapter<com.example.healthstore.MycartAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<ShoppingCart> items;


    private Context mContext;


// Provide a reference to the views for each data item


    public MycartAdapter(Context ctx, ArrayList<ShoppingCart> items) {

        inflater = LayoutInflater.from(ctx);
        this.items = items;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public MycartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.homerecycleview, parent, false);
        MycartAdapter.MyViewHolder holder = new MycartAdapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.healthstore.MycartAdapter.MyViewHolder holder, int position) {

        // holder.iv.setImageResource(items.get(position).());
        ShoppingCart currentItem = items.get(position);
        double price = currentItem.getPrice();
        String image = currentItem.getPicture();


        Picasso.get().load(image).fit().centerInside().into(holder.iv);

        //Glide.with(MycartAdapter.this.mContext.getApplicationContext()).load(items.get(position).getImage_drawable()).into(holder.iv);

        holder.price.setText("€" + Double.toString(price));
        holder.name.setText(items.get(position).getName());
        holder.description.setText(items.get(position).getDescription());


    }


    // Return the size of your dataset
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView iv;
        TextView name;
        TextView price;
        TextView description;


        public MyViewHolder(View itemView) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name);
            price =  itemView.findViewById(R.id.price);


        }


    }
    public void filterlist(ArrayList<ShoppingCart> filteredlist){

        items = filteredlist;
        notifyDataSetChanged();

    }
}

