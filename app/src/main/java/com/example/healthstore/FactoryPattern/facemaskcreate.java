package com.example.healthstore.FactoryPattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.healthstore.Product;
import com.example.healthstore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class facemaskcreate extends AppCompatActivity {

    EditText color;
    EditText pictureaddress;
    Button addproduct;
    Spinner type;
    Facemask mask;
    private DatabaseReference mRootRef1 = FirebaseDatabase.getInstance().getReference().child("Products");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facemaskcreate);

        color = findViewById(R.id.color);
        pictureaddress = findViewById(R.id.pictureaddress);
        addproduct = findViewById(R.id.addproduct);
        type = findViewById(R.id.masktype);

        List<String> masktypes = new ArrayList<>();
        masktypes.add("-Choose your Mask Type-");
        masktypes.add("Clay");
        masktypes.add("Mud");
        masktypes.add("Peel off");

        ArrayAdapter<String> maskadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, masktypes);
        maskadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(maskadapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosentype = type.getSelectedItem().toString();

                FaceFactory factory = new FaceFactory();
                mask = factory.getType(chosentype);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
addproduct.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Product createProduct = new Product();
        createProduct.setProductID("21");
        createProduct.setManufacturer(mask.Manufacturer());
        createProduct.setCategroy(mask.Manufacturer());
        createProduct.setDescription(mask.description());
        createProduct.setPrice(mask.price());
        createProduct.setName(color.getText().toString() + " " + mask.name());
        createProduct.setStock(21);
        createProduct.setImage_drawable(pictureaddress.getText().toString());
        mRootRef1.child(createProduct.getName()).setValue(createProduct);

    }
});


    }
}
