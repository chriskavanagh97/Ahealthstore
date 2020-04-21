package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String TAG = "MainActivity";
    TextView logmail, logpassword, regemail, regpassword, regconfrimpass, regaddress, regname, number;

    private String UserID;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Users");
    private DatabaseReference mRootRef1 = FirebaseDatabase.getInstance().getReference().child("Products");


    Button Login, Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        Register = findViewById(R.id.Register);
        Login = findViewById(R.id.Login);
        regemail = findViewById(R.id.Regemailaddress);
        regpassword = findViewById(R.id.Regpassword);
        logmail = findViewById(R.id.Loginmailaddress);
        logpassword = findViewById(R.id.Loginpassword);
        regconfrimpass = findViewById(R.id.Regconfirmpassword);
        regaddress = findViewById(R.id.Address);
        number = findViewById(R.id.Phone);
        regname = findViewById(R.id.regname);


        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View c) {
                String Email = logmail.getText().toString().trim();
                String pword = logpassword.getText().toString().trim();
                if (Email.isEmpty()) {
                    logmail.setError("Please enter Email");
                    logmail.requestFocus();
                } else if (pword.isEmpty()) {
                    logpassword.setError("Please enter Password");
                    logpassword.requestFocus();
                } else {


                    //=========================================================================================================================================================================================
                    //Here we then sign in the user with the Firebase Authenticaiton
                    //=========================================================================================================================================================================================

                    mFirebaseAuth.signInWithEmailAndPassword(Email, pword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful() & logmail.toString().contains("adminhealth")) {
                                startActivity(new Intent(MainActivity.this, Homescreenadmin.class));

                            } else if(task.isSuccessful()) {
                                startActivity(new Intent(MainActivity.this, HomeScreen.class));

                            }else{
                                Toast.makeText(MainActivity.this, "Login Error, Please Login Again!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }
        });


     Register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String Email = regemail.getText().toString();
            final String pword = regpassword.getText().toString();
            String confirmpword = regconfrimpass.getText().toString();
            final String name = regname.getText().toString();
            final String address = regaddress.getText().toString();
            final String pnumber = number.getText().toString();
            final String creditcardid = null;

            if (Email.isEmpty()) {
                regemail.setError("Please enter Email");
                regemail.requestFocus();
            } else if (pword.isEmpty()) {
                regpassword.setError("Please enter Password");
                regpassword.requestFocus();
            }   else if (name.isEmpty()) {
                regname.setError("Please enter Name");
                regname.requestFocus();
            }   else if (confirmpword.isEmpty()) {
                regconfrimpass.setError("Please enter username");
                regconfrimpass.requestFocus();
            }
            else if (pnumber.isEmpty()) {
                number.setError("Please enter a phone number");
                number.requestFocus();
            }
            else if (address.isEmpty()) {
                regaddress.setError("Please enter an address");
                regaddress.requestFocus();



            } else {
                mFirebaseAuth.createUserWithEmailAndPassword(Email, pword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "onComplete: Failed=" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        } else
                        {

                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            UserID = Objects.requireNonNull(user).getUid();
                            boolean adminvalue = false;
                            boolean discount = false;

                            User createUser = new User(Email, name, address, pnumber, creditcardid, adminvalue, discount);
                            createUser.setEmail(Email);
                            createUser.setName(name);
                            createUser.setAddress(address);
                            createUser.setPhone(pnumber);
                            createUser.setCreditcardid(UserID);
                            if(createUser.getEmail().contains("@adminhealth.com"))
                            {
                                createUser.setAdmin(true);
                                createUser.setDiscount(true);
                                mRootRef.child(UserID).setValue(createUser);


                                startActivity(new Intent(MainActivity.this, Homescreenadmin.class));

                            }
                            else{
                                createUser.setAdmin(false);

                                mRootRef.child(UserID).setValue(createUser);


                                startActivity(new Intent(MainActivity.this, HomeScreen.class));

                            }



                        }
                    }


                });
            }

        }

    });
}
    protected void onStart() {
        super.onStart();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments");

        String name = "";
        String comment = " ";
        Comment newcomment = new Comment(name , comment);
        newcomment.setComment("This product has really helped me with my skin, vitamin d is defintley needed and this is the right product to supply it ");
        newcomment.setUsername("Chris Kavanagh");

        reference.child("Vitamin D").child("1").setValue(newcomment);

        Comment newcomment1 = new Comment(name , comment);
        newcomment1.setComment("Really didnt like this product I saw no benefits to my skin at all ");
        newcomment1.setUsername("Michael Gilllen");

        reference.child("Vitamin D").child("2").setValue(newcomment1);

        Comment newcomment2 = new Comment(name , comment);
        newcomment2.setComment("My health and skin both have taken a real boost since using this product I am really happy ");
        newcomment2.setUsername("Harry Thomas");

        reference.child("Vitamin D").child("3").setValue(newcomment2);

        Comment newcomment3 = new Comment(name , comment);
        newcomment3.setComment("A very good product I definitely recommend it, I will continue to use this product for as long as I can happy and healthy !");
        newcomment3.setUsername("Paddy Matthews");

        reference.child("Vitamin D").child("4").setValue(newcomment3);
        String productID = "1";
        String Manufacturer= null;
        String Categroy = null;
        String Description = null;
        String ISBN = null;
        double Price = 0;
        String Name = null;
        String DiscountPrice = null; ;
        String image_drawable = null;
        int stock = 0;


        Product createProduct = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable, stock);
        createProduct.setProductID("1");
        createProduct.setManufacturer("Together");
        createProduct.setCategroy("Vitamins");
        createProduct.setDescription("Vitamin D to help your skin flourish and adds great nutrients to your system");
        createProduct.setPrice(10.99);
        createProduct.setName("Vitamin D");
        createProduct.setStock(21);
        createProduct.setImage_drawable("https://boots.scene7.com/is/image/Boots/10259415?op_sharpen=1");


        mRootRef1.child(createProduct.getName()).setValue(createProduct);
        productID = "2";
        Product createProduct2 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable, stock);
        createProduct2.setProductID("2");
        createProduct2.setManufacturer("Berocca");
        createProduct2.setCategroy("Vitamins");
        createProduct2.setDescription("Supplying your body with the required vitamin C to give you an energy boost");
        createProduct2.setPrice(7.99);
        createProduct2.setName("Berocca");
        createProduct2.setStock(15);
        createProduct2.setImage_drawable("https://boots.scene7.com/is/image/Boots/10131468?op_sharpen=1");


        mRootRef1.child(createProduct2.getName()).setValue(createProduct2);


        productID = "3";
        Product createProduct3 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable, stock);
        createProduct3.setProductID("1");
        createProduct3.setManufacturer("Centrum");
        createProduct3.setCategroy("Vitamins");
        createProduct3.setDescription("Helps increase yours body immunity to infection and disease");
        createProduct3.setPrice(5.99);
        createProduct3.setName("Centrum immunity");
        createProduct3.setStock(9);
        createProduct3.setImage_drawable("https://boots.scene7.com/is/image/Boots/10141600?op_sharpen=1");

        mRootRef1.child(createProduct3.getName()).setValue(createProduct3);

        productID = "4";
        Product createProduct4 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct4.setProductID("4");
        createProduct4.setManufacturer("My protein");
        createProduct4.setCategroy("Protein");
        createProduct4.setDescription("Chocolate flavour Vegan protein completly dairy free helps repair your muscles.");
        createProduct4.setPrice(21.99);
        createProduct4.setStock(199);
        createProduct4.setName("Vegan Protein");
        createProduct4.setImage_drawable("https://boots.scene7.com/is/image/Boots/10272958?op_sharpen=1");
        mRootRef1.child(createProduct4.getName()).setValue(createProduct4);


        productID = "5";
        Product createProduct5 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct5.setProductID("5");
        createProduct5.setManufacturer("Optimum Nutrition");
        createProduct5.setCategroy("Protein");
        createProduct5.setDescription("Gold Standard protein whey the best protein you can buy. Premium recovery");
        createProduct5.setPrice(24.99);
        createProduct5.setStock(25);
        createProduct5.setName("Gold Standard Protein");
        createProduct5.setImage_drawable("https://boots.scene7.com/is/image/Boots/10269173?op_sharpen=1");
        mRootRef1.child(createProduct5.getName()).setValue(createProduct5);


        productID = "6";
        Product createProduct6 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable,  stock);
        createProduct6.setProductID("6");
        createProduct6.setManufacturer("PHD");
        createProduct6.setCategroy("Protein");
        createProduct6.setDescription("Salted Caramel protein drink");
        createProduct6.setPrice(2.99);
        createProduct6.setStock(21);
        createProduct6.setName("Lean Protein Shake");
        createProduct6.setImage_drawable("https://boots.scene7.com/is/image/Boots/10272944?op_sharpen=1");
        mRootRef1.child(createProduct6.getName()).setValue(createProduct6);


        productID = "11";
        Product createProduct11 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable, stock);
        createProduct11.setProductID("11");
        createProduct11.setManufacturer("Nivea");
        createProduct11.setCategroy("Suncream");
        createProduct11.setStock(11);
        createProduct11.setDescription("The highest and best standard suncream to help you save your sin during hot summers");
        createProduct11.setPrice(10.99);
        createProduct11.setName("Factor 50 Suncream");
        createProduct11.setImage_drawable("https://boots.scene7.com/is/image/Boots/10050488?op_sharpen=1");

        mRootRef1.child(createProduct11.getName()).setValue(createProduct11);


        productID = "8";
        Product createProduct8 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct8.setProductID("8");
        createProduct8.setManufacturer("7 heaven");
        createProduct8.setCategroy("Face Mask");
        createProduct8.setDescription("Black sea weed peel off face masks");
        createProduct8.setPrice(2.99);
        createProduct8.setName("Black Seaweed Face mask");
        createProduct8.setImage_drawable("https://boots.scene7.com/is/image/Boots/10171933?id=-Klmv1&fmt=jpg&fit=constrain,1&wid=504&hei=548");
        mRootRef1.child(createProduct8.getName()).setValue(createProduct8);

        productID = "7";
        Product createProduct7 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct7.setProductID("7");
        createProduct7.setManufacturer("7 heaven");
        createProduct7.setCategroy("Face Mask");
        createProduct7.setDescription("Black charcoal peel off face masks");
        createProduct7.setPrice(2.99);
        createProduct7.setStock(21);

        createProduct7.setName("Black charcoal Face mask");
        createProduct7.setImage_drawable("https://boots.scene7.com/is/image/Boots/10259655?id=-Klmv1&fmt=jpg&fit=constrain,1&wid=504&hei=548");
        mRootRef1.child(createProduct7.getName()).setValue(createProduct7);


        productID = "9";
        Product createProduct9 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct9.setProductID("9");
        createProduct9.setManufacturer("7 heaven");
        createProduct9.setCategroy("Face Mask");
        createProduct9.setDescription("Cucumber peel off face mask nourish your skin");
        createProduct9.setPrice(2.99);
        createProduct9.setStock(21);

        createProduct9.setName("Cucumber Face Mask");
        createProduct9.setImage_drawable("https://boots.scene7.com/is/image/Boots/10094530?id=-Klmv1&fmt=jpg&fit=constrain,1&wid=504&hei=548");


        mRootRef1.child(createProduct9.getName()).setValue(createProduct9);


        productID = "10";
        Product createProduct10 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable , stock);
        createProduct10.setProductID("10");
        createProduct10.setManufacturer("7 heaven");
        createProduct10.setCategroy("Face Mask");
        createProduct10.setDescription("passion peel off face mask nourish your skin");
        createProduct10.setPrice(2.99);
        createProduct10.setStock(21);

        createProduct10.setName("Passion Face Mask");
        createProduct10.setImage_drawable("https://boots.scene7.com/is/image/Boots/10094545?id=-Klmv1&fmt=jpg&fit=constrain,1&wid=504&hei=548");



        mRootRef1.child(createProduct10.getName()).setValue(createProduct10);

       /* Product createProduct = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct.setProductID("1");
        createProduct.setManufacturer("health foods");
        createProduct.setCategroy("Vitamins");
        createProduct.setDescription("Vitamin D to help your skin flourish and adds great nutrients to your system");
        createProduct.setPrice(10.99);
        createProduct.setName("Vitamin D");
        createProduct.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/50suncream.jpg");


        mRootRef.child(UserID).setValue(createProduct);
        productID = "2";
        Product createProduct = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct.setProductID("1");
        createProduct.setManufacturer("health foods");
        createProduct.setCategroy("Vitamins");
        createProduct.setDescription("Vitamin D to help your skin flourish and adds great nutrients to your system");
        createProduct.setPrice(10.99);
        createProduct.setName("Vitamin D");
        createProduct.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/50suncream.jpg");


        mRootRef.child(UserID).setValue(createProduct);*/


    }
}