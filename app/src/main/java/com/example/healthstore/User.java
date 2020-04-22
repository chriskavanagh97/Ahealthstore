package com.example.healthstore;

import com.example.healthstore.Decorator.UserType;

public class User implements UserType {

    String Email;
    String Name;
    String Address;
    String Phone;
    String Creditcardid;
    boolean admin;
    boolean discount;

    public User(String emai, String name, String address, String phone, String creditcardid , boolean admin, boolean discount) {
        Email = emai;
        Name = name;
        Address = address;
        Phone = phone;
        Creditcardid = creditcardid;
        this.admin = admin;
        this.discount = discount;

    }

    public User(String email, String name, String address) {
        Email = email;
        Name = name;
        Address = address;
    }

    public User(){

    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String emai) {
        Email = emai;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCreditcardid() {
        return Creditcardid;
    }

    public void setCreditcardid(String creditcardid) {
        Creditcardid = creditcardid;
    }

    @Override
    public String login() {
        return null;
    }
}