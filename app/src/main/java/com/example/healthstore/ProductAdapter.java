package com.example.healthstore;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<com.example.healthstore.ProductAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Product> products;
    String email;


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();
    private Context mContext;


// Provide a reference to the views for each data item


    public ProductAdapter(Context ctx, ArrayList<Product> products) {

        inflater = LayoutInflater.from(ctx);
        this.products = products;


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

        // holder.iv.setImageResource(products.get(position).());
        Product currentItem = products.get(position);
        double price = currentItem.getPrice();
        String image = currentItem.getImage_drawable();
        String id = currentItem.getProductID();

        Picasso.get().load(image).fit().centerInside().into(holder.iv);

        //Glide.with(ProductAdapter.this.mContext.getApplicationContext()).load(products.get(position).getImage_drawable()).into(holder.iv);

        holder.price.setText("€" + Double.toString(price));
        holder.name.setText(products.get(position).getName());

        final Product model = products.get(position);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), ProductView.class);

                intent.putExtra("name",model.getName());
                v.getContext().startActivity(intent);




            }
        });
    }


    // Return the size of your dataset
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView iv;
        TextView name;
        TextView price;



        public MyViewHolder(View itemView) {
            super(itemView);


            iv = (ImageView) itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name);
            price =  itemView.findViewById(R.id.price);


        }


    }
    public void filterlist(ArrayList<Product> filteredlist){

        products = filteredlist;
        notifyDataSetChanged();

    }





    }


