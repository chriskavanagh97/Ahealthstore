package com.example.healthstore;

public class Product {

    String productID;
    String Manufacturer;
    String Categroy;
    String Description;
    double Price;
    String Name;
    String image_drawable;
    int stock;

    public Product(String productID, String manufacturer, String categroy, String description, double price, String name, String image_drawable, int stock) {
        this.productID = productID;
        Manufacturer = manufacturer;
        Categroy = categroy;
        Description = description;
        Price = price;
        Name = name;
        this.image_drawable = image_drawable;
        this.stock = stock;
    }

    public Product(double price, String name, String image_drawable) {
        Price = price;
        Name = name;
        this.image_drawable = image_drawable;
    }

    public Product(){

    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(String image_drawable) {
        this.image_drawable = image_drawable;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getCategroy() {
        return Categroy;
    }

    public void setCategroy(String categroy) {
        Categroy = categroy;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
