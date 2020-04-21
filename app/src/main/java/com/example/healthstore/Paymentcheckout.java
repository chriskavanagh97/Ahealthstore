package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Paymentcheckout extends AppCompatActivity {
    EditText cardname, cardnumber, cvv, month, year;
    Spinner s1;
    Button b1;

    Order order;
    ArrayList<Product> products;

    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    String productnames;
    private DatabaseReference reference, ref2, ref3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentcheckout);


        cardname = findViewById(R.id.cardname);
        cardnumber = findViewById(R.id.checkout_cardnum);
        cvv = findViewById(R.id.cvv_editText);
        month = findViewById(R.id.expiry_month);
        year = findViewById(R.id.expiry_year);
        s1 = findViewById(R.id.cardtype);
        b1 = findViewById(R.id.confirm_order_button);

        order = new Order();
        products = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        final double total = intent.getDoubleExtra("total", 0);
       productnames =  intent.getStringExtra("products");


        List<String> cardTypes = new ArrayList<>();
        cardTypes.add("-Choose your Card Type-");
        cardTypes.add("MasterCard");
        cardTypes.add("American Express");
        cardTypes.add("Visa");

        ArrayAdapter<String> cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cardTypes);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(cardAdapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String method = s1.getSelectedItem().toString();

                order.setPaymentMethod(method);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ref2 = FirebaseDatabase.getInstance().getReference().child("Orders").child(user.getUid());

        ref3 = FirebaseDatabase.getInstance().getReference().child("My Cart").child(user.getUid());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                order.setTotal(total);
                order.setUser(user.getEmail());
                order.setItems(productnames);

                ref2.push().setValue(order);

                ref3.removeValue();


                Toast.makeText(Paymentcheckout.this, "Order Confirmed!", Toast.LENGTH_LONG).show();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}