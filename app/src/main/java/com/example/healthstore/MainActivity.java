package com.example.healthstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    }
}