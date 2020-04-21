package com.example.healthstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Orderadapter extends RecyclerView.Adapter<com.example.healthstore.Orderadapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<Order> orders;
    String email;


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();
    private Context mContext;


// Provide a reference to the views for each data item


    public Orderadapter(Context ctx, ArrayList<Order> orders) {

        inflater = LayoutInflater.from(ctx);
        this.orders = orders;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public Orderadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.orderhistoryview, parent, false);
        Orderadapter.MyViewHolder holder = new Orderadapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.healthstore.Orderadapter.MyViewHolder holder, int position) {

        // holder.iv.setImageResource(orders.get(position).());
        Order currentorder = orders.get(position);
        double cost = currentorder.getTotal();




        holder.totalcost.setText(Double.toString(cost));
        holder.products.setText(orders.get(position).getItems());



            }




    // Return the size of your dataset
    public int getItemCount() {
        return orders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView products;
        TextView totalcost;
        TextView address;

        public MyViewHolder(View itemView) {
            super(itemView);


            totalcost = itemView.findViewById(R.id.totalcost);
            products = itemView.findViewById(R.id.products);



        }
    }
}
