package com.example.healthstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.healthstore.Comment;
import com.example.healthstore.R;

import java.util.ArrayList;

public class CommentAdapter  extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{

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

        View view = inflater.inflate(R.layout.recycleviewcomment, parent, false);
        CommentAdapter.MyViewHolder holder = new CommentAdapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(CommentAdapter.MyViewHolder holder, int position) {

        // holder.iv.setImageResource(comments.get(position).());
        Comment currentItem = comments.get(position);

        String name = currentItem.getUsername();
        String comment = currentItem.getComment();

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

}

