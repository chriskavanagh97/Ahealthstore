package com.example.healthstore;

public class ShoppingCart {

    String id;
    String name;
    double price;
    String picture;
    String Description;



    public ShoppingCart( String name, double price, String picture, String Description) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.Description = Description;
    }
    public ShoppingCart(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
