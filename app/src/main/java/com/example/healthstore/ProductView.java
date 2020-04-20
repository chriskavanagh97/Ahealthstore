package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductView extends AppCompatActivity {

    TextView name, price, rating, description;
    ImageView productimage;
    String Name , Rating , Description , image;
    Double Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

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
    }
}
