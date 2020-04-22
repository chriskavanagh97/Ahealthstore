package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthstore.Adapter.CommentAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminProductView extends AppCompatActivity {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments");
    private ArrayList<Comment> comments = new ArrayList<>();
    CommentAdapter commentAdapter;
    RecyclerView recyclerView;
    EditText commenttext;
    String username;
    boolean admin;
    EditText ratingvalue;
    Double ratingtotal;

    //Stock level features
    LinearLayout stocklevels;
    Button stocklevelbtn ;
    TextView stocklevel ;


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    final String userid = mFirebaseAuth.getCurrentUser().getUid();

    TextView name, price, rating, description;
    ImageView productimage;
    String Name , Rating , Description , image;
    Double Price;
    Button addcomment , addrating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_view);

        recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);

        commentAdapter = new CommentAdapter(this, comments);
        recyclerView.setAdapter(commentAdapter);





//========================================================
//Product View details
// ======================================================
        Intent intent = getIntent();
        String nameintent = intent.getStringExtra("name");

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        productimage = findViewById(R.id.imageView);
        description = findViewById(R.id.description);

        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Products").child(String.valueOf(nameintent));
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
                price.setText(Double.toString(Price));
                Picasso.get().load(image).fit().centerInside().into(productimage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//==============================================================================
        // Comments Adapter
//===================================================================================

        reference.child("Vitamin D").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                comments.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Comment comment = dataSnapshot1.getValue(Comment.class);
                    comments.add(new Comment(comment.getUsername(), comment.getComment()));

                }


                commentAdapter = new CommentAdapter(AdminProductView.this, comments);
                recyclerView.setAdapter(commentAdapter);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        commenttext = findViewById(R.id.comment);


        ratingvalue = findViewById(R.id.ratingvalue);




        Button cart = findViewById(R.id.Cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShoppingCart newitem = new ShoppingCart();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("My Cart").child(userid);

                newitem.setName(name.getText().toString());
                newitem.setPrice(Price);
                newitem.setId("1");
                newitem.setPicture(image);
                newitem.setDescription(Description);

                myRef.push().setValue(newitem);

            }
        });
        Button editvalues = findViewById(R.id.updateproduct);
        editvalues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AdminProductView.this);
                alertDialogBuilder.setTitle("Warning");
                alertDialogBuilder.setMessage("Are you sure you want to edit "+ name.getText().toString() + "'s details");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products").child(Name);
                        reference.addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                                                                    double pricevalue = Double.parseDouble(price.getText().toString());

                                                                    DatabaseReference nameref = dataSnapshot.getRef().child("name");
                                                                    nameref.setValue(name.getText().toString());

                                                                    DatabaseReference priceref = dataSnapshot.getRef().child("price");
                                                                    priceref.setValue(pricevalue);

                                                                    DatabaseReference descriptionref = dataSnapshot.getRef().child("description");
                                                                    descriptionref.setValue(description.getText().toString());

                                                                }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {


                                                            }
                                                        }
                        );


                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminProductView.this, "Changes were not made", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialogBuilder.create().show();

            }
        });

    }

    protected void onStart() {
        super.onStart();


        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                username = user.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Ratings").child(name.getText().toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Rating rating = dataSnapshot1.getValue(Rating.class);
                    ratingtotal = rating.getRating();

                }

                Toast.makeText(AdminProductView.this, "rating total " + ratingtotal + name.getText(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}
