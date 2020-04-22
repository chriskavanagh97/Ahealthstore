package com.example.healthstore.Sorting;

import com.example.healthstore.Product;

import java.util.ArrayList;
import java.util.Collections;

public class SortByManufacturer implements SortingStrategy {

    @Override
    public ArrayList<Product> ascendingOrder(ArrayList<Product> products) {
        Collections.sort(products, (o1, o2) -> o1.getManufacturer().compareTo(o2.getManufacturer()));



        return products;
    }

    @Override
    public ArrayList<Product> descendingOrder(ArrayList<Product> products) {
        Collections.sort(products, (o1, o2) -> o1.getManufacturer().compareTo(o2.getManufacturer()));
        Collections.reverse(products);


        return products;
    }
}
