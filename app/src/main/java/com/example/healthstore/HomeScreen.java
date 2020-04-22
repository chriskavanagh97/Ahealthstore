package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.healthstore.Adapter.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");
    private ArrayList<Product> products = new ArrayList<>();
    Spinner mySpinner;

    private RecyclerView recyclerView;
    ProductAdapter adapter;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = findViewById(R.id.my_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        mySpinner = (Spinner) findViewById(R.id.values);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(HomeScreen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.values));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter((myAdapter));


        Button sortsearch = (Button) findViewById(R.id.sortsearch);
        sortsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String sortrequest = mySpinner.getSelectedItem().toString();
                if (sortrequest.equals("alphabetically")){
                    value = "name";

            } else if (sortrequest.equals("price ascending"))
                {
                        value = "price";
                }
                else if (sortrequest.equals("price descending"))
                {
                    value = "price";
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(HomeScreen.this);
                    mLayoutManager.setStackFromEnd(true);

                    GridLayoutManager layout = new GridLayoutManager(HomeScreen.this, 2);
                    layout.setReverseLayout(true);
                    recyclerView.setLayoutManager(layout);
                }
                Query queryRef = reference.orderByChild(value).limitToLast(10);
                queryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        products.clear();

                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {

                            Product productitem = dataSnapshot1.getValue(Product.class);
                            if (productitem.isState() == false) {

                            } else {


                                products.add(new Product(productitem.getPrice(), productitem.getName(), productitem.getImage_drawable()));
                            }
                        }

                        adapter = new ProductAdapter(HomeScreen.this, products);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





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
Button cart = findViewById(R.id.Cart);
cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeScreen.this,ViewMyCart.class);
        startActivity(intent);

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
