package com.example.healthstore.StatePattern;

public class OutofStock implements StockState{

    public boolean stateOfStock() {
        return false;
    }

}