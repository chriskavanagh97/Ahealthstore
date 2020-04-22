package com.example.healthstore.Sorting;

import com.example.healthstore.Product;

import java.util.ArrayList;
import java.util.Collections;

public class SortByName implements SortingStrategy {

    @Override
    public ArrayList<Product> ascendingOrder(ArrayList<Product> products) {
        Collections.sort(products, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        return products;
    }

    @Override
    public ArrayList<Product> descendingOrder(ArrayList<Product> products) {
        Collections.sort(products, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Collections.reverse(products);

        return products;
    }

}