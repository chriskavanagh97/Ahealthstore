package com.example.healthstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<com.example.healthstore.ProductAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Product> imageModelArrayList;


    private Context mContext;
// Provide a reference to the views for each data item


    public ProductAdapter(Context ctx, ArrayList<Product> imageModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.homerecycleview, parent, false);
        ProductAdapter.MyViewHolder holder = new ProductAdapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.healthstore.ProductAdapter.MyViewHolder holder, int position) {

        //holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        final Product model = imageModelArrayList.get(position);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Intent intent = new Intent(v.getContext(), AnimalView.class);

                intent.putExtra("name", model.getName());
                v.getContext().startActivity(intent);*/


            }
        });
    }

    // Return the size of your dataset
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView iv;
        TextView name;
        TextView description;



        public MyViewHolder(View itemView) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name);
            description =  itemView.findViewById(R.id.description);


        }
    }
}

