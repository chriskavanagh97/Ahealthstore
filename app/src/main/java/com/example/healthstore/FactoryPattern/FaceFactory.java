package com.example.healthstore.FactoryPattern;

public class FaceFactory {

    public Facemask getType(String facemasktype) {
        if (facemasktype == "clay") {
            return new ClayMask();
        } else if (facemasktype == "Mud") {
            return new MudMask();
        } else if (facemasktype == "Peel off") {
            return new Peeloff();
        }
        return null;

    }
}
