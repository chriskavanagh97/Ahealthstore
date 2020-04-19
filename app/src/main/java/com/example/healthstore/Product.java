package com.example.healthstore;

public class Product {

    String productID;
    String Manufacturer;
    String Categroy;
    String Description;
    String ISBN;
    String Price;
    String Name;
    String DiscountPrice;
    int image_drawable;

    public Product(String productID, String manufacturer, String categroy, String description, String ISBN, String price, String name, String description1, String discountPrice, int image_drawable) {
        this.productID = productID;
        Manufacturer = manufacturer;
        Categroy = categroy;
        Description = description;
        this.ISBN = ISBN;
        Price = price;
        Name = name;
        Description = description1;
        DiscountPrice = discountPrice;
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

    public String getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        DiscountPrice = discountPrice;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
