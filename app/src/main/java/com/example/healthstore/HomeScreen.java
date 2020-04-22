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
import com.example.healthstore.Adapter.ProductAdapterAdmin;
import com.example.healthstore.Iterator.Interface;
import com.example.healthstore.Sorting.SortByCategory;
import com.example.healthstore.Sorting.SortByManufacturer;
import com.example.healthstore.Sorting.SortByName;
import com.example.healthstore.Sorting.SortByPrice;
import com.example.healthstore.Sorting.SortingContext;
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

                SortingContext context = new SortingContext();

                if(sortrequest.equals("Title Ascending")) {
                    context.setSortingMethod(new SortByName());
                    context.sortAscending(products);
                }
                else if(sortrequest.equals("Title Descending")) {
                    context.setSortingMethod(new SortByName());
                    context.sortDescending(products);
                }
                else if(sortrequest.equals("Price Ascending")) {
                    context.setSortingMethod(new SortByPrice());
                    context.sortAscending(products);
                }
                else if(sortrequest.equals("Price Descending"))
                {
                    context.setSortingMethod(new SortByPrice());
                    context.sortDescending(products);
                }
                else if(sortrequest.equals("Manufacturer Ascending")) {
                    context.setSortingMethod(new SortByManufacturer());
                    context.sortAscending(products);
                }
                else if(sortrequest.equals("Manufacturing Descending")) {
                    context.setSortingMethod(new SortByManufacturer());
                    context.sortDescending(products);
                }

                else if(sortrequest.equals("Category Ascending")) {
                    context.setSortingMethod(new SortByCategory());
                    context.sortAscending(products);
                }
                else if(sortrequest.equals("Category Descending")) {
                    context.setSortingMethod(new SortByCategory());
                    context.sortDescending(products);
                }
                adapter = new ProductAdapter(HomeScreen.this, products);
                recyclerView.setAdapter(adapter);
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

            Button ordrs = findViewById(R.id.orders);
            ordrs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(HomeScreen.this, Orderhistory.class);
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
