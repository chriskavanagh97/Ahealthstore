package com.example.healthstore.Decorator;

public class Admin implements UserType {

    public Admin() {

    }

    public Admin(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String login() {

        return "AdminUser";
    }
}