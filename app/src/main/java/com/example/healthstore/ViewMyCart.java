package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewMyCart extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ShoppingCarts");
    private ArrayList<ShoppingCart> items = new ArrayList<>();
    MycartAdapter mycartadapter;
    double subtotal;
    RecyclerView recyclerView;

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_cart);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("My Cart").child(userid);


        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);

        mycartadapter = new MycartAdapter(this, items);
        recyclerView.setAdapter(mycartadapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ShoppingCart shoppingCart = dataSnapshot1.getValue(ShoppingCart.class);
                    items.add(new ShoppingCart(shoppingCart.getName(), shoppingCart.getPrice(), shoppingCart.getPicture(), shoppingCart.getDescription()));
                    subtotal = subtotal + shoppingCart.getPrice();

                }


                mycartadapter = new MycartAdapter(ViewMyCart.this, items);
                recyclerView.setAdapter(mycartadapter);

                TextView subtotalview = findViewById(R.id.SubTotal);
                subtotalview.setText(Double.toString(subtotal));

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
