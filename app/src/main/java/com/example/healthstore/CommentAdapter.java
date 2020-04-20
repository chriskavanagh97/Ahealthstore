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

public class CommentAdapter  extends RecyclerView.Adapter<com.example.healthstore.CommentAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Comment> comments;


    private Context mContext;


// Provide a reference to the views for each data item


    public CommentAdapter(Context ctx, ArrayList<Comment> comments) {

        inflater = LayoutInflater.from(ctx);
        this.comments = comments;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.homerecycleview, parent, false);
        CommentAdapter.MyViewHolder holder = new CommentAdapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.healthstore.CommentAdapter.MyViewHolder holder, int position) {

        // holder.iv.setImageResource(comments.get(position).());
        Comment currentItem = comments.get(position);

        String name = currentItem.getUsername();
        String comment = currentItem.getComment();



        //Glide.with(CommentAdapter.this.mContext.getApplicationContext()).load(comments.get(position).getImage_drawable()).into(holder.iv);

        holder.comment.setText(comments.get(position).getComment());
        holder.name.setText(comments.get(position).getUsername());


    }


    // Return the size of your dataset
    public int getItemCount() {
        return comments.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView comment;
        TextView name;




        public MyViewHolder(View itemView) {
            super(itemView);


            comment = itemView.findViewById(R.id.comment);
            name = itemView.findViewById(R.id.name);




        }


    }
    public void filterlist(ArrayList<Comment> filteredlist){

        comments = filteredlist;
        notifyDataSetChanged();

    }
}

