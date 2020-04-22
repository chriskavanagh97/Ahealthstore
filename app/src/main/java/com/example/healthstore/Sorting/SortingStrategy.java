package com.example.healthstore.Sorting;

import com.example.healthstore.Product;

import java.util.ArrayList;

public interface SortingStrategy {

    public ArrayList<Product> ascendingOrder(ArrayList<Product> products);
    public ArrayList<Product> descendingOrder(ArrayList<Product> products);


}