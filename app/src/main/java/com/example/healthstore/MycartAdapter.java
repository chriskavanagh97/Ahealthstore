package com.example.healthstore;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MycartAdapter  extends RecyclerView.Adapter<com.example.healthstore.MycartAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<ShoppingCart> items;
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();

    private Context mContext;


// Provide a reference to the views for each data item


    public MycartAdapter(Context ctx, ArrayList<ShoppingCart> items) {

        inflater = LayoutInflater.from(ctx);
        this.items = items;


    }


    // Create new views (invoked by the layout manager)
    @Override
    public MycartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.shoppingcartview, parent, false);
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
        final ShoppingCart model = items.get(position);

        holder.removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                Query removeQuery = ref.child("My Cart").child(userid).orderByChild("name").equalTo(model.getName());
                removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot mysnapshot: dataSnapshot.getChildren()) {
                            mysnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });


            }
        });


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
        TextView removeitem;

        public MyViewHolder(View itemView) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price =  itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            removeitem = itemView.findViewById(R.id.removeitem);


        }


    }
    public void filterlist(ArrayList<ShoppingCart> filteredlist){

        items = filteredlist;
        notifyDataSetChanged();

    }
}

