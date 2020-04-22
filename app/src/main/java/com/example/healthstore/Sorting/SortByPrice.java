package com.example.healthstore.Sorting;

import com.example.healthstore.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByPrice implements SortingStrategy {

    @Override
    public ArrayList<Product> ascendingOrder(ArrayList<Product> products) {

        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product c1, Product c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());
            }
        });



        return products;
    }

    @Override
    public ArrayList<Product> descendingOrder(ArrayList<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product c1, Product c2) {
                return Double.compare(c1.getPrice(), c2.getPrice());
            }
        });
        Collections.reverse(products);


        return products;
    }
}
