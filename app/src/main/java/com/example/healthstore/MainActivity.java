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
                                startActivity(new Intent(MainActivity.this, HomePage.class));

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


                            startActivity(new Intent(MainActivity.this, HomePage.class));
                        }
                    }


                });
            }

        }

    });
}

}