package com.example.healthstore.FactoryPattern;

public interface  Facemask {

String description();
String name();
String Manufacturer();
double price();
String Category();
}

class Peeloff implements Facemask{

    @Override
    public String description() {
        return "Vitamin E are Anti-oxidants that help protect skin, while pores get a deep clean peel-off.";
    }

    @Override
    public String name() {
        return "Peel off mask";
    }

    @Override
    public String Manufacturer() {
        return "seven heaven";
    }

    @Override
    public double price() {
        return 2.99;
    }

    @Override
    public String Category() {
        return "FaceMask";
    }
}
class MudMask implements Facemask{

    @Override
    public String description() {
        return "Mud to draw out impurities and open blocked pores to give clean, soft skin in a refreshing mud pack";
    }

    @Override
    public String name() {
        return "Mud mask";
    }

    @Override
    public String Manufacturer() {
        return "seven heaven";
    }

    @Override
    public double price() {
        return 1.99;
    }

    @Override
    public String Category() {
        return "FaceMask";
    }
}

class ClayMask implements Facemask{

    @Override
    public String description() {
        return "he creamy texture detoxifies the skin's surface by cleansing deep into the pores, leaving the skin looking clarified and beautified without drying it out";
    }

    @Override
    public String name() {
        return "Clay mask";
    }

    @Override
    public String Manufacturer() {
        return "seven heaven";
    }

    @Override
    public double price() {
        return 6.99;
    }

    @Override
    public String Category() {
        return "FaceMask";
    }
}