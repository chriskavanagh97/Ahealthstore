package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orderhistory extends AppCompatActivity {



    Orderadapter adapater;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Orders");
    RecyclerView recyclerView;
    private ArrayList<Order> orders = new ArrayList<>();
    String name;


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");




        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(mLayoutManager);



        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                orders.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Order order = dataSnapshot1.getValue(Order.class);
                    orders.add(new Order(order.getTotal(), order.getItems()));

                }


                adapater = new Orderadapter(Orderhistory.this, orders);
                recyclerView.setAdapter(adapater);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}
}
