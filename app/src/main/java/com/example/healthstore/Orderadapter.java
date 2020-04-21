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
    private ArrayList<User> users;
    String email;


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();
    private Context mContext;


// Provide a reference to the views for each data item


    public Orderadapter(Context ctx, ArrayList<User> users) {

        inflater = LayoutInflater.from(ctx);
        this.users = users;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public Orderadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.userrecycleview, parent, false);
        Orderadapter.MyViewHolder holder = new Orderadapter.MyViewHolder(view);

        return holder;


    }


    @Override
    public void onBindViewHolder(com.example.healthstore.Orderadapter.MyViewHolder holder, int position) {

        // holder.iv.setImageResource(users.get(position).());
        User currentUser = users.get(position);
        String address = currentUser.getAddress();
        String name = currentUser.getName();
        String mail = currentUser.getEmail();


        //Glide.with(Orderadapter.this.mContext.getApplicationContext()).load(users.get(position).getImage_drawable()).into(holder.iv);

        holder.email.setText(users.get(position).getEmail());
        holder.name.setText(users.get(position).getName());
        holder.address.setText(users.get(position).getAddress());

        final User model = users.get(position);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), AdminProductView.class);

                intent.putExtra("name", model.getName());
                v.getContext().startActivity(intent);


            }
        });
    }


    // Return the size of your dataset
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView email;
        TextView name;
        TextView address;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.Email);
            address = itemView.findViewById(R.id.Address);


        }
    }
}
