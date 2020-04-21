package com.example.healthstore;

public class User {

    String Email;
    String Name;
    String Address;
    String Phone;
    String Creditcardid;
    boolean admin;

    public User(String emai, String name, String address, String phone, String creditcardid , boolean admin) {
        Email = emai;
        Name = name;
        Address = address;
        Phone = phone;
        Creditcardid = creditcardid;
        this.admin = admin;
    }

    public User(){

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
}