package com.example.healthstore.Sorting;

import com.example.healthstore.Product;

import java.util.ArrayList;

public class SortingContext {

    private SortingStrategy strategy;

    public void setSortingMethod(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public SortingStrategy getStrategy() {
        return strategy;
    }

    public ArrayList<Product> sortAscending(ArrayList<Product> p){
        return strategy.ascendingOrder(p);
    }

    public ArrayList<Product> sortDescending(ArrayList<Product> p){
        return strategy.descendingOrder(p);
    }
}