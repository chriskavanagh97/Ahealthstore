package com.example.healthstore;

public class Product {

    String productID;
    String Manufacturer;
    String Categroy;
    String Description;
    double Price;
    String Name;
    int image_drawable;

    public Product(String productID, String manufacturer, String categroy, String description, double price, String name,  int image_drawable) {
        this.productID = productID;
        Manufacturer = manufacturer;
        Categroy = categroy;
        Description = description;
        Price = price;
        Name = name;
        this.image_drawable = image_drawable;
    }

    public Product(double price, String name, int image_drawable) {
        Price = price;
        Name = name;
        this.image_drawable = image_drawable;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
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
