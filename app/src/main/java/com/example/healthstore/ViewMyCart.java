package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewMyCart extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ShoppingCarts");
    private ArrayList<ShoppingCart> items = new ArrayList<>();
    MycartAdapter mycartadapter;
    double subtotal;
    RecyclerView recyclerView;
    TextView subtotalview;
    TextView discountview;
    double discount;
    String discountrounded;
    String subtotalrounded;
    private static DecimalFormat df = new DecimalFormat("0.00");

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_cart);
        subtotalview = findViewById(R.id.SubTotal);
        discountview = findViewById(R.id.Discount);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("My Cart").child(userid);
        final DatabaseReference referenceuseer = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);


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
                items.clear();
                subtotal = 0;

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    ShoppingCart shoppingCart = dataSnapshot1.getValue(ShoppingCart.class);
                    items.add(new ShoppingCart(shoppingCart.getName(), shoppingCart.getPrice(), shoppingCart.getPicture(), shoppingCart.getDescription()));
                    subtotal = subtotal + shoppingCart.getPrice();

                }


                mycartadapter = new MycartAdapter(ViewMyCart.this, items);
                recyclerView.setAdapter(mycartadapter);

                referenceuseer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final User user = dataSnapshot.getValue(User.class);
                        if(user.isDiscount()){

                            df.setRoundingMode(RoundingMode.UP);

                            discount = subtotal * .90;
                            subtotalrounded =df.format(discount);
                            discountrounded = df.format(subtotal - discount);

                            subtotalview.setText(subtotalrounded);
                            discountview.setText(discountrounded);


                        }
                        else {
                            subtotalrounded =df.format(subtotal);
                            subtotalview.setText(subtotalrounded);
                            discountview.setText(0);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMyCart.this, Paymentcheckout.class);
                intent.putExtra("total", subtotalrounded);
                startActivity(intent);
            }
        });

    }
}
