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

                            if (task.isSuccessful()) {
                                startActivity(new Intent(MainActivity.this, HomeScreen.class));

                            } else {

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


                            User createUser = new User(Email, name, address, pnumber, creditcardid);
                            createUser.setEmail(Email);
                            createUser.setName(name);
                            createUser.setAddress(address);
                            createUser.setPhone(pnumber);
                            createUser.setCreditcardid(UserID);


                            mRootRef.child(UserID).setValue(createUser);


                            startActivity(new Intent(MainActivity.this, HomeScreen.class));
                        }
                    }


                });
            }

        }

    });
}
    protected void onStart() {
        super.onStart();


       /* String productID = "1";
        String Manufacturer= null;
        String Categroy = null;
        String Description = null;
        String ISBN = null;
        double Price = 0;
        String Name = null;
        String DiscountPrice = null; ;
        String image_drawable = null;


        Product createProduct = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct.setProductID("1");
        createProduct.setManufacturer("Together");
        createProduct.setCategroy("Vitamins");
        createProduct.setDescription("Vitamin D to help your skin flourish and adds great nutrients to your system");
        createProduct.setPrice(10.99);
        createProduct.setName("Vitamin D");
        createProduct.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/vitamind.jpg");


        mRootRef1.child(productID).setValue(createProduct);
        productID = "2";
        Product createProduct2 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct2.setProductID("2");
        createProduct2.setManufacturer("Berocca");
        createProduct2.setCategroy("Vitamins");
        createProduct2.setDescription("Supplying your body with the required vitamin C to give you an energy boost");
        createProduct2.setPrice(7.99);
        createProduct2.setName("Berocca");
        createProduct2.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/borocca.jpg");


        mRootRef1.child(productID).setValue(createProduct2);


        productID = "3";
        Product createProduct3 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct3.setProductID("1");
        createProduct3.setManufacturer("Centrum");
        createProduct3.setCategroy("Vitamins");
        createProduct3.setDescription("Helps increase yours body immunity to infection and disease");
        createProduct3.setPrice(5.99);
        createProduct3.setName("Centrum immunity");
        createProduct3.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/centrum.jpg");

        mRootRef1.child(productID).setValue(createProduct3);

        productID = "4";
        Product createProduct4 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct4.setProductID("4");
        createProduct4.setManufacturer("My protein");
        createProduct4.setCategroy("Protein");
        createProduct4.setDescription("Chocolate flavour Vegan protein completly dairy free helps repair your muscles.");
        createProduct4.setPrice(21.99);
        createProduct4.setName("Vegan Protein");
        createProduct4.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/chocolateprotein.jpg");
        mRootRef1.child(productID).setValue(createProduct4);


        productID = "5";
        Product createProduct5 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct5.setProductID("5");
        createProduct5.setManufacturer("Optimum Nutrition");
        createProduct5.setCategroy("Protein");
        createProduct5.setDescription("Gold Standard protein whey the best protein you can buy. Premium recovery");
        createProduct5.setPrice(24.99);
        createProduct5.setName("Gold Standard Protein");
        createProduct5.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/premiumwhey.jpg");
        mRootRef1.child(productID).setValue(createProduct5);


        productID = "6";
        Product createProduct6 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct6.setProductID("6");
        createProduct6.setManufacturer("PHD");
        createProduct6.setCategroy("Protein");
        createProduct6.setDescription("Salted Caramel protein drink");
        createProduct6.setPrice(2.99);
        createProduct6.setName("Lean Protein Shake");
        createProduct6.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/proteindrink.jpg");
        mRootRef1.child(productID).setValue(createProduct6);


        productID = "7";
        Product createProduct7 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct7.setProductID("7");
        createProduct7.setManufacturer("health foods");
        createProduct7.setCategroy("Vitamins");
        createProduct7.setDescription("Vitamin D to help your skin flourish and adds great nutrients to your system");
        createProduct7.setPrice(10.99);
        createProduct7.setName("Vitamin D");
        createProduct7.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/50suncream.jpg");

        mRootRef1.child(productID).setValue(createProduct7);


        productID = "8";
        Product createProduct8 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct8.setProductID("8");
        createProduct8.setManufacturer("7 heaven");
        createProduct8.setCategroy("Face Mask");
        createProduct8.setDescription("Black sea weed peel off face masks");
        createProduct8.setPrice(2.99);
        createProduct8.setName("Black Seaweed Face mask");
        createProduct8.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/blackseaweed.jpg");
        mRootRef1.child(productID).setValue(createProduct8);


        productID = "9";
        Product createProduct9 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct9.setProductID("9");
        createProduct9.setManufacturer("7 heaven");
        createProduct9.setCategroy("Face Mask");
        createProduct9.setDescription("Cucumber peel off face mask nourish your skin");
        createProduct9.setPrice(2.99);
        createProduct9.setName("Cucumber Face Mask");
        createProduct9.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/cucumber.jpg");


        mRootRef1.child(productID).setValue(createProduct9);


        productID = "10";
        Product createProduct10 = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
        createProduct10.setProductID("10");
        createProduct10.setManufacturer("7 heaven");
        createProduct10.setCategroy("Face Mask");
        createProduct10.setDescription("passion peel off face mask nourish your skin");
        createProduct10.setPrice(2.99);
        createProduct10.setName("Passion Face Mask");
        createProduct10.setImage_drawable("https://firebasestorage.googleapis.com/v0/b/myhealthstore-d68ad.appspot.com/passionmask.jpg");



        mRootRef1.child(productID).setValue(createProduct10);

        Product createProduct = new Product(productID, Manufacturer, Categroy, Description, Price, Name, image_drawable);
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