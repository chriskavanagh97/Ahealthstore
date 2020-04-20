package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductView extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments");
    private ArrayList<Comment> comments = new ArrayList<>();
    CommentAdapter adapter;
    private RecyclerView recyclerView;



    TextView name, price, rating, description;
    ImageView productimage;
    String Name , Rating , Description , image;
    Double Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);


        recyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        //price = findViewById(R.id.rating);
        productimage = findViewById(R.id.imageView);
        description = findViewById(R.id.description);


        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Products").child(String.valueOf(1));
        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Product product = dataSnapshot.getValue(Product.class);
                Price = product.getPrice();
                Name = product.getName();
                //Rating = product.get
                Description = product.getDescription();
                image = product.getImage_drawable();


                name.setText(Name);
                description.setText(Description);
                price.setText("€" + Double.toString(Price));
                Picasso.get().load(image).fit().centerInside().into(productimage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference.child(name.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if( dataSnapshot.getChildrenCount() == 0){

                    comments.add(new Comment(" ", "There are no comments"));

                }
                else {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Comment comment = dataSnapshot1.getValue(Comment.class);
                        comments.add(new Comment( comment.getUsername(), comment.getComment()));

                    }


                    adapter = new CommentAdapter(ProductView.this, comments);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
