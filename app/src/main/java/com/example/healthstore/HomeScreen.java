package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");
    private ArrayList<Product> products = new ArrayList<>();


    private RecyclerView recyclerView;
    ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        recyclerView = findViewById(R.id.my_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        EditText search = (EditText) findViewById(R.id.search);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                Product productitem = dataSnapshot1.getValue(Product.class);
                products.add(new Product(productitem.getPrice(),productitem.getName(),productitem.getImage_drawable()));

                }


                adapter = new ProductAdapter(HomeScreen.this, products);
                recyclerView.setAdapter(adapter);
        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });



    }

    private void filter(String text)
    {
        ArrayList<Product> filteredList = new ArrayList<Product>();

        for (Product item : products)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }
        adapter.filterlist(filteredList);
    }
}
